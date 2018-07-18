package br.com.caelum.casadocodigo.activity;

import android.app.Application;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.com.caelum.casadocodigo.R;
import br.com.caelum.casadocodigo.adapter.ItensAdapter;
import br.com.caelum.casadocodigo.application.CasaDoCodigoApplication;
import br.com.caelum.casadocodigo.dagger.DaggerCasaDoCodigoComponent;
import br.com.caelum.casadocodigo.modelo.Carrinho;
import br.com.caelum.casadocodigo.modelo.Item;
import br.com.caelum.casadocodigo.modelo.Livro;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CarrinhoActivity extends AppCompatActivity {


    @BindView(R.id.lista_itens_carrinho)
    RecyclerView lista;

    @BindView(R.id.valor_carrinho)
    TextView valorDaCompra;

    private Carrinho carrinho;

    @Inject
    public void setCarrinho(Carrinho carrinho) {
        this.carrinho = carrinho;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrinho);
        ButterKnife.bind(this);

        CasaDoCodigoApplication app = (CasaDoCodigoApplication) getApplication();
        DaggerCasaDoCodigoComponent component = app.getComponent();
        component.inject(this);

        List<Item> itens = carrinho.getItens();

        lista.setAdapter(new ItensAdapter(itens, this));

        lista.setLayoutManager(new LinearLayoutManager(this));

        valorDaCompra.setText(carrinho.valorTotal());

    }


}
