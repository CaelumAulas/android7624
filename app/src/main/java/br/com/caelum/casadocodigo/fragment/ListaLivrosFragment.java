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

public class ListaLivrosFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_lista_livros, container, false);


        RecyclerView listaDeLivros = (RecyclerView) view.findViewById(R.id.fragment_lista_livros);

        List<Livro> livros = criaLivros();


        LivroAdapter adapter = new LivroAdapter(livros);


        listaDeLivros.setAdapter(adapter);


        listaDeLivros.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;

    }

    private List<Livro> criaLivros() {
        List<Livro> livros = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {

            Livro livro = new Livro();
            livro.setNome("Nome " + i);
            livros.add(livro);
        }
        return livros;
    }
}