package com.example.AppPublico.api;

import com.example.AppPublico.models.EventoResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface EventoService {
    @GET("eventos")
    Call<EventoResponse> listarEventos(
            @Header("Authorization") String token,
            @Query("page") int page,
            @Query("size") int size
        );
}
