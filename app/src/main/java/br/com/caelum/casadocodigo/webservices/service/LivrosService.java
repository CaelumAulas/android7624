package br.com.caelum.casadocodigo.webservices.service;

import java.util.ArrayList;

import br.com.caelum.casadocodigo.modelo.Livro;
import retrofit2.Call;
import retrofit2.http.GET;

public interface LivrosService {

    @GET("listarLivros?indicePrimeiroLivro=0&qtdLivros=20")
    Call<ArrayList<Livro>> buscaLivros();
}
