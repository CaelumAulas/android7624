package br.com.caelum.casadocodigo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.casadocodigo.R;
import br.com.caelum.casadocodigo.adapter.LivroAdapter;
import br.com.caelum.casadocodigo.modelo.Livro;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ListaLivrosFragment extends Fragment {

    private static final String LISTA = "lista";


    @BindView(R.id.fragment_lista_livros)
    RecyclerView listaDeLivros;

    public static ListaLivrosFragment com(ArrayList<Livro> livros) {
        ListaLivrosFragment fragment = new ListaLivrosFragment();

        Bundle arguments = new Bundle();

        arguments.putSerializable(LISTA, livros);

        fragment.setArguments(arguments);

        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_lista_livros, container, false);

        ButterKnife.bind(this, view);

        ArrayList<Livro> livros = recuperaLista();

        carregaLista(livros);

        listaDeLivros.setLayoutManager(new LinearLayoutManager(getContext()));


        return view;

    }

    private ArrayList<Livro> recuperaLista() {
        Bundle arguments = getArguments();

        return (ArrayList<Livro>) arguments.getSerializable(LISTA);
    }

    public void carregaLista(List<Livro> livros) {

        LivroAdapter adapter = new LivroAdapter(livros);


        listaDeLivros.setAdapter(adapter);
    }

}
