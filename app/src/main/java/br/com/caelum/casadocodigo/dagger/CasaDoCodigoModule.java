package br.com.caelum.casadocodigo.dagger;

import java.util.ArrayList;

import javax.inject.Singleton;

import br.com.caelum.casadocodigo.modelo.Carrinho;
import br.com.caelum.casadocodigo.webservices.WebClient;
import dagger.Module;
import dagger.Provides;

@Module // módulo serve pra indicar como construir as dependências
public class CasaDoCodigoModule {

    @Provides
    @Singleton
    public Carrinho getCarrinho() {
        return new Carrinho();
    }

}


