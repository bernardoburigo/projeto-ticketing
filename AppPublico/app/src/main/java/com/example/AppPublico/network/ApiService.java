package com.example.AppPublico.network;

import com.example.AppPublico.models.MensagemSistema;
import com.example.AppPublico.models.PaginarOrBuscarLoteIngressoResponseDTO;
import com.example.AppPublico.models.UsuarioRequest;
import com.example.AppPublico.models.VendaRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {

    @POST("/usuarios/login")
    Call<LoginResponse> login(@Body LoginRequest request);

    @POST("/usuarios/cadastrar")
    Call<Void> cadastrarUsuarioPublico(@Body UsuarioRequest request);

    @GET("lotes-ingressos/evento/{idEvento}")
    Call<PaginarOrBuscarLoteIngressoResponseDTO> buscarLotesPorEvento(@Path("idEvento") int idEvento);

    @POST("vendas/evento/{idEvento}/usuario/{idUsuario}")
    Call<MensagemSistema> comprarIngresso(@Path("idEvento") int idEvento,
                                          @Path("idUsuario") int idUsuario,
                                          @Body VendaRequest dto);

}