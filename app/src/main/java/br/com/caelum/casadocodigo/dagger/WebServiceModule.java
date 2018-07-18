package br.com.caelum.casadocodigo.dagger;

import javax.inject.Singleton;

import br.com.caelum.casadocodigo.converter.LivroServiceConverterFactory;
import br.com.caelum.casadocodigo.webservices.service.LivrosService;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class WebServiceModule {

    private final String url;

    public WebServiceModule(String url) {
        this.url = url;
    }

    @Provides
    @Singleton
    public Retrofit getRetrofit(LivroServiceConverterFactory livroServiceConverterFactory) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(livroServiceConverterFactory)
                //.addConverterFactory(JacksonConverterFactory.create())
                .build();

        return retrofit;
    }

    @Provides
    @Singleton
    public LivroServiceConverterFactory getLivroServiceConverterFactory() {
        return new LivroServiceConverterFactory();
    }

    @Provides
    @Singleton
    public LivrosService getLivrosService(Retrofit retrofit) {
        LivrosService service = retrofit.create(LivrosService.class);
        return service;
    }
    
}