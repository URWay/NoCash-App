package com.example.luiz1.nocash.service;

import com.example.luiz1.nocash.Model.Cliente;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ClienteService {

    public static final String BASE_URL = "http://localhost:8080/nocash/ws/clientes/";

    /**
     *  Serviço de login do cliente
     */
    @POST("login")
    Call<Cliente> Login(@Body Cliente cliente);

    /**
    *  Serviço de cadastro do cliente
    */
    @POST
    Call<Cliente> Cadastro(@Body Cliente cliente);
}
