package br.com.caelum.casadocodigo.webservices;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import br.com.caelum.casadocodigo.converter.LivroServiceConverterFactory;
import br.com.caelum.casadocodigo.modelo.Livro;
import br.com.caelum.casadocodigo.webservices.service.LivrosService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class WebClient {

    public static final String URL = "http://cdcmob.herokuapp.com/";

    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(new LivroServiceConverterFactory())
            .build();



    public void pegaLivros(int indice) {

        LivrosService service = retrofit.create(LivrosService.class);

        Call<ArrayList<Livro>> call = service.buscaLivros(indice,10);

        call.enqueue(new Callback<ArrayList<Livro>>() {
            @Override
            public void onResponse(Call<ArrayList<Livro>> call, Response<ArrayList<Livro>> response) {
                ArrayList<Livro> livros = response.body();

                EventBus.getDefault().post(livros);
            }

            @Override
            public void onFailure(Call<ArrayList<Livro>> call, Throwable erro) {

                EventBus.getDefault().post(erro);
            }
        });

    }
}
