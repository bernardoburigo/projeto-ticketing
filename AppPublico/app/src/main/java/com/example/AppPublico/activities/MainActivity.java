package com.example.AppPublico.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.AppPublico.R;
import com.example.AppPublico.adapters.EventoAdapter;
import com.example.AppPublico.api.EventoService;
import com.example.AppPublico.models.Evento;
import com.example.AppPublico.models.EventoResponse;
import com.example.AppPublico.services.RetrofitClient;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private EventoAdapter adapter;
    private BottomNavigationView bottomNavigation;
    private boolean doubleBackToExitPressedOnce = false;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences prefs = getSharedPreferences("AppPrefs", Context.MODE_PRIVATE);
        boolean isLoggedIn = prefs.getBoolean("isLoggedIn", false);
        token = prefs.getString("jwtToken", null);

        if (!isLoggedIn || token == null) {
            Intent intent = new Intent(this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            return;
        }

        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerEventos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setSelectedItemId(R.id.nav_home);

        bottomNavigation.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_home) return true;
            if (id == R.id.nav_search) {
                startActivity(new Intent(this, PesquisaActivity.class));
                overridePendingTransition(0, 0);
                return true;
            }
            if (id == R.id.nav_user) {
                startActivity(new Intent(this, UsuarioActivity.class));
                overridePendingTransition(0, 0);
                return true;
            }
            return false;
        });

        carregarEventosEmDestaque();
    }

    private void carregarEventosEmDestaque() {
        EventoService service = RetrofitClient.getClient(this).create(EventoService.class);
        Call<EventoResponse> call = service.listarEventos("Bearer " + token, 0, 3);

        call.enqueue(new Callback<EventoResponse>() {
            @Override
            public void onResponse(Call<EventoResponse> call, Response<EventoResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Evento> eventos = response.body().getContent();
                    adapter = new EventoAdapter(eventos, evento -> {
                        Intent intent = new Intent(MainActivity.this, DetalhesEventoActivity.class);
                        intent.putExtra("evento", evento);
                        startActivity(intent);
                    });
                    recyclerView.setAdapter(adapter);
                } else {
                    Toast.makeText(MainActivity.this, "Erro ao carregar eventos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<EventoResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Erro: " + t.getMessage(), Toast.LENGTH_LONG).show();
                Log.e("MainActivity", "Erro ao buscar eventos", t);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (bottomNavigation != null) {
            bottomNavigation.setSelectedItemId(R.id.nav_home);
        }
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Pressione novamente para sair", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(() -> doubleBackToExitPressedOnce = false, 2000);
    }
}