package com.example.luiz1.nocash.service;

import com.example.luiz1.nocash.Model.Movimento;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface MovimentoService {

    public static final String BASE_URL = "https://carteiravirtualws.azurewebsites.net/nocash/ws/";

    /**
     *  Serviço para recarga da carteira
     */
    @POST("movimento/carga")
    Call<Void> cargaMovimento(@Body Movimento movimento);

}
