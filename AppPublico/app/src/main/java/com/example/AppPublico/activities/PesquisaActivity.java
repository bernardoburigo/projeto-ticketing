package com.example.AppPublico.activities;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.AppPublico.R;
import com.example.AppPublico.adapters.EventoAdapter;
import com.example.AppPublico.models.CategoriaEvento;
import com.example.AppPublico.models.Evento;
import com.example.AppPublico.network.ApiService;
import com.example.AppPublico.services.RetrofitClient;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PesquisaActivity extends AppCompatActivity {

    private EditText edtBuscarEvento;
    private Spinner spinnerCategorias;
    private RecyclerView recyclerEventos;
    private EventoAdapter eventoAdapter;
    private List<Evento> eventos = new ArrayList<>();
    private List<CategoriaEvento> categorias = new ArrayList<>();
    private BottomNavigationView bottomNavigation;
    private boolean isLoggedIn = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisa);

        edtBuscarEvento = findViewById(R.id.edtBuscarEvento);
        spinnerCategorias = findViewById(R.id.spinnerCategorias);
        recyclerEventos = findViewById(R.id.recyclerEventos);
        bottomNavigation = findViewById(R.id.bottom_navigation);

        recyclerEventos.setLayoutManager(new LinearLayoutManager(this));

        eventoAdapter = new EventoAdapter(eventos, evento -> {
            Intent intent = new Intent(this, DetalhesEventoActivity.class);
            intent.putExtra("eventoSelecionado", evento);
            startActivity(intent);
        });
        recyclerEventos.setAdapter(eventoAdapter);

        carregarCategorias();
        configurarBottomNavigation();
        buscarEventos(null, null);

        edtBuscarEvento.setOnEditorActionListener((v, actionId, event) -> {
            String nome = edtBuscarEvento.getText().toString();
            String categoria = (String) spinnerCategorias.getSelectedItem();
            buscarEventos(nome.isEmpty() ? null : nome, categoria.equals("Todas") ? null : categoria);
            return true;
        });
    }

    private void carregarCategorias() {
        categorias.clear();
        categorias.add(new CategoriaEvento(null, "Todas"));
        categorias.add(new CategoriaEvento(1, "Show"));
        categorias.add(new CategoriaEvento(2, "Palestra"));
        categorias.add(new CategoriaEvento(3, "Workshop"));
        categorias.add(new CategoriaEvento(4, "Teatro"));
        categorias.add(new CategoriaEvento(5, "Festival"));
        categorias.add(new CategoriaEvento(6, "Esportivo"));
        categorias.add(new CategoriaEvento(7, "Networking"));
        categorias.add(new CategoriaEvento(8, "Beneficente"));
        categorias.add(new CategoriaEvento(9, "Congresso"));
        categorias.add(new CategoriaEvento(10, "Feira"));
        categorias.add(new CategoriaEvento(11, "Exposição"));
        categorias.add(new CategoriaEvento(12, "Treinamento"));
        categorias.add(new CategoriaEvento(13, "Convenção"));
        categorias.add(new CategoriaEvento(14, "Reunião"));
        categorias.add(new CategoriaEvento(15, "Musical"));

        List<String> nomes = new ArrayList<>();
        for (CategoriaEvento cat : categorias) {
            nomes.add(cat.getNome());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, nomes);
        spinnerCategorias.setAdapter(adapter);
    }

    private void buscarEventos(String nome, String categoriaNome) {
        ApiService api = RetrofitClient.getApiService();
        Call<List<Evento>> call = api.buscarEventos(nome, categoriaNome);

        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<Evento>> call, Response<List<Evento>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    eventos.clear();
                    eventos.addAll(response.body());
                    eventoAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(PesquisaActivity.this, "Erro ao buscar eventos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Evento>> call, Throwable t) {
                Toast.makeText(PesquisaActivity.this, "Falha: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void configurarBottomNavigation() {
        bottomNavigation.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_home) navigateTo(MainActivity.class);
            else if (id == R.id.nav_user) navigateTo(UsuarioActivity.class);
            return true;
        });
    }

    private void navigateTo(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        overridePendingTransition(0, 0);
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        bottomNavigation.setSelectedItemId(R.id.nav_search);
    }
}
