package br.com.caelum.casadocodigo.webservices.service;

import java.util.ArrayList;

import br.com.caelum.casadocodigo.modelo.Livro;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LivrosService {

    @GET("listarLivros")
    Call<ArrayList<Livro>> buscaLivros(
            @Query("indicePrimeiroLivro") int indicePrimeiroLivro,
             @Query("qtdLivros") int qtdLivros);
}
