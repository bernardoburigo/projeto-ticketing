package com.example.AppPublico.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.AppPublico.R;
import com.example.AppPublico.adapters.IngressoAdapter;
import com.example.AppPublico.models.PageResponse;
import com.example.AppPublico.models.QrCodeResponseDTO;
import com.example.AppPublico.network.ApiService;
import com.example.AppPublico.services.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MeusIngressosActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private IngressoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meus_ingressos);

        recyclerView = findViewById(R.id.recyclerIngressos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        SharedPreferences prefs = getSharedPreferences("AppPrefs", MODE_PRIVATE);
        int idUsuario = prefs.getInt("idUsuario", -1);

        if (idUsuario == -1) {
            Toast.makeText(this, "Usuário não identificado", Toast.LENGTH_SHORT).show();
            return;
        }

        ApiService api = RetrofitClient.getApiService();
        api.getIngressosPorUsuario(idUsuario, 0, 50).enqueue(new Callback<PageResponse<QrCodeResponseDTO>>() {
            @Override
            public void onResponse(Call<PageResponse<QrCodeResponseDTO>> call, Response<PageResponse<QrCodeResponseDTO>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<QrCodeResponseDTO> ingressos = response.body().getContent();
                    adapter = new IngressoAdapter(ingressos, MeusIngressosActivity.this);
                    recyclerView.setAdapter(adapter);
                } else {
                    Toast.makeText(MeusIngressosActivity.this, "Erro ao carregar ingressos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PageResponse<QrCodeResponseDTO>> call, Throwable t) {
                Toast.makeText(MeusIngressosActivity.this, "Erro de rede: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
