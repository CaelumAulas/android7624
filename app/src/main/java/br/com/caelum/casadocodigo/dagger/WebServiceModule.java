package br.com.caelum.casadocodigo.dagger;

import javax.inject.Singleton;

import br.com.caelum.casadocodigo.converter.LivroServiceConverterFactory;
import br.com.caelum.casadocodigo.webservices.service.DeviceService;
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
                //.axddConverterFactory(JacksonConverterFactory.create())
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
    public DeviceService getDeviceService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080")
                .build();

        DeviceService service = retrofit.create(DeviceService.class);
        return service;
    }

    @Provides
    @Singleton
    public LivrosService getLivrosService(Retrofit retrofit) {
        LivrosService service = retrofit.create(LivrosService.class);
        return service;
    }

}