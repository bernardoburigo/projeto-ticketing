package com.example.AppPublico.activities;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.AppPublico.R;
import com.example.AppPublico.adapters.HistoricoIngressoAdapter;
import com.example.AppPublico.models.HistoricoIngresso;

import java.util.ArrayList;
import java.util.List;

public class MeusIngressosActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private HistoricoIngressoAdapter adapter;
    private List<HistoricoIngresso> listaMockada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meus_ingressos);

        recyclerView = findViewById(R.id.recyclerViewIngressos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listaMockada = gerarIngressosMock();

        adapter = new HistoricoIngressoAdapter(listaMockada, ingresso ->
                Toast.makeText(this, "Ingresso: " + ingresso.getNomeEvento(), Toast.LENGTH_SHORT).show()
        );

        recyclerView.setAdapter(adapter);
    }

    private List<HistoricoIngresso> gerarIngressosMock() {
        List<HistoricoIngresso> lista = new ArrayList<>();
        lista.add(new HistoricoIngresso("Tech Summit 2025", "01/07/2025", "Rua da Tecnologia, 123", "10:00", "R$40,00"));
        lista.add(new HistoricoIngresso("Festival de Música Indie", "15/08/2025", "Parque Central", "20:00", "R$80,00"));
        lista.add(new HistoricoIngresso("Seminário de IA", "22/09/2025", "Auditório UF", "14:00", "R$200,50"));
        return lista;
    }
}
