package br.com.caelum.casadocodigo.webservices.service;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface DeviceService {

    @GET("/device/register/{email}/{id}")
    Call<String> cadastraDevice(@Path("email") String email,
                                @Path("id") String id);

}
