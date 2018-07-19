package br.com.caelum.casadocodigo.webservices;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import br.com.caelum.casadocodigo.modelo.Livro;
import br.com.caelum.casadocodigo.webservices.service.DeviceService;
import br.com.caelum.casadocodigo.webservices.service.LivrosService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Singleton
public class WebClient {

    private LivrosService service;

    private DeviceService deviceService;


    @Inject
    public WebClient(LivrosService service, DeviceService deviceService) {
        this.service = service;
        this.deviceService = deviceService;
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

    public List<Livro> buscaLivros() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public void cadastraDevice(String id, String email){
        deviceService.cadastraDevice(email,id).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }


}
