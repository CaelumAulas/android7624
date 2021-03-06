package br.com.caelum.casadocodigo.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import br.com.caelum.casadocodigo.R;
import br.com.caelum.casadocodigo.application.CasaDoCodigoApplication;
import br.com.caelum.casadocodigo.dagger.DaggerCasaDoCodigoComponent;
import br.com.caelum.casadocodigo.modelo.Carrinho;
import br.com.caelum.casadocodigo.modelo.Item;
import br.com.caelum.casadocodigo.modelo.Livro;
import br.com.caelum.casadocodigo.modelo.TipoDeCompra;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetalhesLivroFragment extends Fragment {


    @BindView(R.id.detalhes_livro_descricao)
    TextView descricao;

    @BindView(R.id.detalhes_livro_autores)
    TextView autores;

    @BindView(R.id.detalhes_livro_nome)
    TextView nome;

    @BindView(R.id.detalhes_livro_foto)
    ImageView foto;

    @BindView(R.id.detalhes_livro_data_publicacao)
    TextView data;

    @BindView(R.id.detalhes_livro_isbn)
    TextView isbn;

    @BindView(R.id.detalhes_livro_num_paginas)
    TextView paginas;

    private Carrinho carrinho;
    private Livro livro;

    @Inject
    public void setCarrinho(Carrinho carrinho) {
        this.carrinho = carrinho;
    }

    public static DetalhesLivroFragment com(Livro livro){
        DetalhesLivroFragment detalhes = new DetalhesLivroFragment();

        Bundle argumentos = new Bundle();

        argumentos.putSerializable("livro", livro);

        detalhes.setArguments(argumentos);

        return detalhes;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_detalhes_livro, container, false);

        ButterKnife.bind(this, view);

        CasaDoCodigoApplication app =
                (CasaDoCodigoApplication) getActivity().getApplication();
        DaggerCasaDoCodigoComponent component = app.getComponent();
        component.inject(this);

        Bundle arguments = getArguments();

        livro = (Livro) arguments.getSerializable("livro");

        nome.setText(livro.getNome());
        Picasso.get().load(livro.getUrlFoto())
                .placeholder(R.drawable.livro).fit().into(foto);
        // teria que fazer os outros bindings

        return view;

    }

    @OnClick(R.id.detalhes_livro_comprar_fisico)
    public void comprarLivroFisico() {
        carrinho.adiciona(new Item(livro, TipoDeCompra.FISICO));
        Toast.makeText(getContext(), "Livro adicionado com sucesso", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.detalhes_livro_comprar_ebook)
    public void comprarLivroEbook() {
        carrinho.adiciona(new Item(livro, TipoDeCompra.VIRTUAL));
        Toast.makeText(getContext(), "Livro adicionado com sucesso", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.detalhes_livro_comprar_ambos)
    public void comprarLivroAmbos() {
        carrinho.adiciona(new Item(livro, TipoDeCompra.JUNTOS));
        Toast.makeText(getContext(), "Livro adicionado com sucesso", Toast.LENGTH_SHORT).show();
    }

}















