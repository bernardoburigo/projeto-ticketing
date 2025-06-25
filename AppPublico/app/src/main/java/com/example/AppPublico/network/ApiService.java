package com.example.AppPublico.network;

import com.example.AppPublico.models.UsuarioRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {

    @POST("/usuarios/login")
    Call<LoginResponse> login(@Body LoginRequest request);

    @POST("/usuarios/cadastrar")
    Call<Void> cadastrarUsuarioPublico(@Body UsuarioRequest request);
}