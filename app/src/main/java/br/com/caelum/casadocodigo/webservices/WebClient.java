package br.com.caelum.casadocodigo.webservices;

import java.util.ArrayList;

import br.com.caelum.casadocodigo.converter.LivroServiceConverterFactory;
import br.com.caelum.casadocodigo.delegate.LivroDelegate;
import br.com.caelum.casadocodigo.modelo.Livro;
import br.com.caelum.casadocodigo.webservices.service.LivrosService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class WebClient {

    public static final String URL = "http://cdcmob.herokuapp.com/";
    private LivroDelegate delegate;

    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(new LivroServiceConverterFactory())
            .build();

    public WebClient(LivroDelegate delegate) {
        this.delegate = delegate;
    }

    public void pegaLivros() {

        LivrosService service = retrofit.create(LivrosService.class);

        Call<ArrayList<Livro>> call = service.buscaLivros();

        call.enqueue(new Callback<ArrayList<Livro>>() {
            @Override
            public void onResponse(Call<ArrayList<Livro>> call, Response<ArrayList<Livro>> response) {
                ArrayList<Livro> livros = response.body();

                delegate.lidaCom(livros);
            }

            @Override
            public void onFailure(Call<ArrayList<Livro>> call, Throwable t) {

                delegate.lidaCom(t);
            }
        });

    }
}
