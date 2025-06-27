package com.example.AppPublico.api;

import com.example.AppPublico.models.CategoriaEvento;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface CategoriaService {
    @GET("/categorias")
    Call<List<CategoriaEvento>> listarCategorias(@Header("Authorization") String token);
}