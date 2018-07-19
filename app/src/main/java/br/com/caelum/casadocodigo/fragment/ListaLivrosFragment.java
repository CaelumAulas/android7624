package br.com.caelum.casadocodigo.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.mugen.Mugen;
import com.mugen.MugenCallbacks;
import com.mugen.attachers.BaseAttacher;

import java.util.ArrayList;

import javax.inject.Inject;

import br.com.caelum.casadocodigo.R;
import br.com.caelum.casadocodigo.adapter.LivroAdapter;
import br.com.caelum.casadocodigo.adapter.LivroInvertidoAdapter;
import br.com.caelum.casadocodigo.application.CasaDoCodigoApplication;
import br.com.caelum.casadocodigo.modelo.Livro;
import br.com.caelum.casadocodigo.webservices.WebClient;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ListaLivrosFragment extends Fragment {

    private static final String LISTA = "lista";


    @BindView(R.id.fragment_lista_livros)
    RecyclerView listaDeLivros;

    private boolean carregando = false;
    private ArrayList<Livro> livros = new ArrayList<>();

    private WebClient webClient;
    private FirebaseRemoteConfig remoteConfig;

    @Inject
    public void setWebClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public static ListaLivrosFragment com(ArrayList<Livro> livros) {
        ListaLivrosFragment fragment = new ListaLivrosFragment();

        Bundle arguments = new Bundle();

        arguments.putSerializable(LISTA, livros);

        fragment.setArguments(arguments);

        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        remoteConfig = FirebaseRemoteConfig.getInstance();


        remoteConfig
                .fetch(15)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {


                        if (task.isSuccessful()) {
                            remoteConfig.activateFetched();
                        }

                    }
                });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_lista_livros, container, false);

        ButterKnife.bind(this, view);

        CasaDoCodigoApplication app = (CasaDoCodigoApplication) getActivity().getApplication();
        app.getComponent().inject(this);

        livros = recuperaLista();

        carregaLista();

        listaDeLivros.setLayoutManager(new LinearLayoutManager(getContext()));


        BaseAttacher attacher = Mugen.with(listaDeLivros, new MugenCallbacks() {
            @Override
            public void onLoadMore() {
                carregando = true;
                webClient.pegaLivros(livros.size());
                Snackbar
                        .make(listaDeLivros, "Carregando mais itens", Snackbar.LENGTH_LONG)
                        .show();
            }

            @Override
            public boolean isLoading() {
                return carregando;
            }

            @Override
            public boolean hasLoadedAllItems() {
                return false;
            }
        }).start();

        attacher.setLoadMoreEnabled(true);

        attacher.setLoadMoreOffset(5);

        return view;

    }

    private ArrayList<Livro> recuperaLista() {
        Bundle arguments = getArguments();

        return (ArrayList<Livro>) arguments.getSerializable(LISTA);
    }

    private void carregaLista() {


        boolean ingles = remoteConfig.getBoolean("idioma");

        RecyclerView.Adapter adapter;

        if (ingles) {
            adapter = new LivroAdapter(livros);

        } else {
            adapter = new LivroInvertidoAdapter(livros);
        }
        listaDeLivros.setAdapter(adapter);


    }

    public void atualizaListaCom(ArrayList<Livro> livros) {

        carregando = false;

        this.livros.addAll(livros);

        listaDeLivros.getAdapter().notifyDataSetChanged();
    }
}
