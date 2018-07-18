package br.com.caelum.casadocodigo.webservices;

import android.util.Log;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Singleton;

import br.com.caelum.casadocodigo.converter.LivroServiceConverterFactory;
import br.com.caelum.casadocodigo.modelo.Livro;
import br.com.caelum.casadocodigo.webservices.service.LivrosService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

@Singleton
public class WebClient {

    private LivrosService service;

    @Inject
    public WebClient(LivrosService service) {
        this.service = service;
    }

    public void pegaLivros(int indice) {

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
