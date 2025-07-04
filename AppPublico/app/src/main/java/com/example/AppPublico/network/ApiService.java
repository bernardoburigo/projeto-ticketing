package com.example.AppPublico.network;

import com.example.AppPublico.models.CategoriaEvento;
import com.example.AppPublico.models.Evento;
import com.example.AppPublico.models.MensagemSistema;
import com.example.AppPublico.models.PageResponse;
import com.example.AppPublico.models.PaginarOrBuscarLoteIngressoResponseDTO;
import com.example.AppPublico.models.QrCodeResponseDTO;
import com.example.AppPublico.models.UsuarioRequest;
import com.example.AppPublico.models.VendaRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

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

    @GET("ingressos/usuario/{idUsuario}")
    Call<PageResponse<QrCodeResponseDTO>> getIngressosPorUsuario(
            @Path("idUsuario") int idUsuario,
            @Query("pagina") int pagina,
            @Query("quantidade") int quantidade
    );

    @GET("/eventos")
    Call<List<Evento>> buscarEventos(@Query("nome") String nome, @Query("categoria") String categoria);


    @GET("/categorias")
    Call<List<CategoriaEvento>> getCategorias();

}