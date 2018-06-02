package com.example.luiz1.nocash.service;

import com.example.luiz1.nocash.Model.Carteira;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface CarteiraService {

    public static final String BASE_URL = "http://localhost:8080/nocash/ws/carteira/";

    /**
     *  Serviço de cadastro da carteira do cliente
     */
    @POST
    Call<Carteira> Cadastro(@Body Carteira carteira);

}
