package br.com.caelum.casadocodigo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import br.com.caelum.casadocodigo.R;
import br.com.caelum.casadocodigo.adapter.ItensAdapter;
import br.com.caelum.casadocodigo.modelo.Carrinho;
import br.com.caelum.casadocodigo.modelo.Item;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CarrinhoActivity extends AppCompatActivity {


    @BindView(R.id.lista_itens_carrinho)
    RecyclerView lista;

    @BindView(R.id.valor_carrinho)
    TextView valorDaCompra;

    private Carrinho carrinho = new Carrinho();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrinho);
        ButterKnife.bind(this);

        List<Item> itens = carrinho.getItens();

        lista.setAdapter(new ItensAdapter(itens, this));

        lista.setLayoutManager(new LinearLayoutManager(this));

        String valorEmString = valorDos(itens);

        valorDaCompra.setText(valorEmString);

    }

    private String valorDos(List<Item> itens) {
        Double valorTotal = 10.0;
        for (Item item : itens) {
            valorTotal += item.getValor();
        }


        NumberFormat format = DecimalFormat.
                getCurrencyInstance(
                        new Locale("pt", "BR"));

        return format.format(valorTotal);
    }
}
