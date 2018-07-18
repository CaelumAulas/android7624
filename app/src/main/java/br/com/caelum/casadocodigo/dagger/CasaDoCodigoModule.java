package br.com.caelum.casadocodigo.dagger;

import java.util.ArrayList;

import javax.inject.Singleton;

import br.com.caelum.casadocodigo.converter.LivroServiceConverterFactory;
import br.com.caelum.casadocodigo.modelo.Carrinho;
import br.com.caelum.casadocodigo.webservices.WebClient;
import br.com.caelum.casadocodigo.webservices.service.LivrosService;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.http.POST;

@Module // módulo serve pra indicar como construir as dependências
public class CasaDoCodigoModule {

    @Provides
    @Singleton
    public Carrinho getCarrinho() {
        return new Carrinho();
    }

}


