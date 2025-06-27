package com.example.AppPublico.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.AppPublico.R;
import com.example.AppPublico.models.Evento;
import com.example.AppPublico.models.LoteIngresso;
import com.example.AppPublico.models.MensagemSistema;
import com.example.AppPublico.models.PaginarOrBuscarLoteIngressoResponseDTO;
import com.example.AppPublico.models.TipoIngressoComLotesResponseDTO;
import com.example.AppPublico.models.VendaItemRequest;
import com.example.AppPublico.models.VendaRequest;
import com.example.AppPublico.network.ApiService;
import com.example.AppPublico.services.RetrofitClient;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class CompraIngressoActivity extends AppCompatActivity {

    private Evento eventoSelecionado;
    private TextView tvNomeEvento, tvLocalEvento, tvPreco;
    private Button btnConfirmarCompra;
    private LoteIngresso loteAtual;
    private int idUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compra_ingresso);

        eventoSelecionado = (Evento) getIntent().getSerializableExtra("eventoSelecionado");

        SharedPreferences prefs = getSharedPreferences("AppPrefs", MODE_PRIVATE);
        Log.d("CompraIngresso", "ID do usuário: " + idUsuario);
        idUsuario = prefs.getInt("idUsuario", -1);

        tvNomeEvento = findViewById(R.id.tvNomeEventoCompra);
        tvLocalEvento = findViewById(R.id.tvLocalEventoCompra);
        tvPreco = findViewById(R.id.tvPrecoIngresso);
        btnConfirmarCompra = findViewById(R.id.btnConfirmarCompra);

        if (eventoSelecionado != null) {
            tvNomeEvento.setText(eventoSelecionado.getNome());
            if (eventoSelecionado.getLocalEvento() != null) {
                tvLocalEvento.setText("Local: " + eventoSelecionado.getLocalEvento().getNome());
            } else {
                tvLocalEvento.setText("Local não informado");
            }
        }

        buscarLoteAtual();

        btnConfirmarCompra.setOnClickListener(v -> realizarCompra());
    }

    private void buscarLoteAtual() {
        ApiService apiService = RetrofitClient.getApiService();
        int eventoId = eventoSelecionado.getId();

        Log.d("CompraIngresso", "Buscando lote para evento ID: " + eventoId);

        Call<PaginarOrBuscarLoteIngressoResponseDTO> call = apiService.buscarLotesPorEvento(eventoId);

        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<PaginarOrBuscarLoteIngressoResponseDTO> call, Response<PaginarOrBuscarLoteIngressoResponseDTO> response) {
                if (response.isSuccessful() && response.body() != null) {
                    PaginarOrBuscarLoteIngressoResponseDTO resposta = response.body();
                    Log.d("CompraIngresso", "Resposta recebida: " + new Gson().toJson(resposta));

                    List<TipoIngressoComLotesResponseDTO> tipos = resposta.getTiposIngresso();

                    if (tipos != null && !tipos.isEmpty()) {
                        TipoIngressoComLotesResponseDTO tipo = tipos.get(0);
                        if (tipo.getLotes() != null && !tipo.getLotes().isEmpty()) {
                            loteAtual = tipo.getLotes().get(0);
                            tvPreco.setText("Preço: R$ " + loteAtual.getPreco());
                            btnConfirmarCompra.setEnabled(true);
                            return;
                        }
                    }

                    Log.e("CompraIngresso", "Nenhum lote disponível.");
                    tvPreco.setText("Nenhum lote disponível");
                    btnConfirmarCompra.setEnabled(false);
                } else {
                    Log.e("CompraIngresso", "Erro ao buscar lote: HTTP " + response.code());
                    Toast.makeText(CompraIngressoActivity.this, "Erro ao buscar lote atual", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PaginarOrBuscarLoteIngressoResponseDTO> call, Throwable t) {
                Log.e("CompraIngresso", "Falha na requisição", t);
                Toast.makeText(CompraIngressoActivity.this, "Erro de rede: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void realizarCompra() {
        if (loteAtual == null) {
            Toast.makeText(this, "Lote ainda não carregado.", Toast.LENGTH_SHORT).show();
            return;
        }

        VendaItemRequest item = new VendaItemRequest(loteAtual.getId(), 1);
        VendaRequest request = new VendaRequest(List.of(item));

        ApiService apiService = RetrofitClient.getApiService();
        Call<MensagemSistema> call = apiService.comprarIngresso(eventoSelecionado.getId(), idUsuario, request);

        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<MensagemSistema> call, Response<MensagemSistema> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(CompraIngressoActivity.this, "Ingresso comprado com sucesso!", Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    try {
                        String errorBody = response.errorBody().string();
                        // Loga o erro no Logcat com tag personalizada
                        Log.e("CompraIngresso", "Erro na compra: " + errorBody);
                        // Também pode mostrar o Toast resumido, se quiser
                        Toast.makeText(CompraIngressoActivity.this, "Erro na compra! Veja log.", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Log.e("CompraIngresso", "Erro ao ler mensagem de erro do servidor", e);
                        Toast.makeText(CompraIngressoActivity.this, "Erro na compra e falha ao ler erro do servidor.", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<MensagemSistema> call, Throwable t) {
                Toast.makeText(CompraIngressoActivity.this, "Falha de rede: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
