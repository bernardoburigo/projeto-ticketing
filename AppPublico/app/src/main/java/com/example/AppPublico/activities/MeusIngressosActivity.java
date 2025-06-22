package com.example.AppPublico.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.AppPublico.R;
import com.example.AppPublico.adapters.IngressoAdapter;
import com.example.AppPublico.models.Evento;
import com.example.AppPublico.models.Ingresso;

import java.util.ArrayList;
import java.util.List;

public class MeusIngressosActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private IngressoAdapter adapter;
    private List<Ingresso> listaMock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meus_ingressos);

        recyclerView = findViewById(R.id.recyclerViewIngressos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listaMock = gerarMockIngressos();

        adapter = new IngressoAdapter(listaMock, ingresso -> {
            Intent intent = new Intent(MeusIngressosActivity.this, DetalhesIngressoActivity.class);
            intent.putExtra("ingresso", ingresso);
            startActivity(intent);
        });

        recyclerView.setAdapter(adapter);
    }

    private List<Ingresso> gerarMockIngressos() {
        List<Ingresso> lista = new ArrayList<>();

        Evento evento1 = new Evento("Tech Summit 2025", "Rua da Tecnologia, 123", "01/07/2025", "10:00", R.drawable.img_evento1, "TechWorld Inc.");
        Evento evento2 = new Evento("Festival de Música Indie", "Parque Central", "15/08/2025", "20:00", R.drawable.img_evento2, "Indie Live");
        Evento evento3 = new Evento("Seminário de IA", "Auditório UF", "22/09/2025", "14:00", R.drawable.img_evento3, "Fórum de Inteligência Artificial");

        lista.add(new Ingresso("ID001", evento1, "20/06/2025", 40.00, "qrCode001"));
        lista.add(new Ingresso("ID002", evento2, "25/06/2025", 80.00, "qrCode002"));
        lista.add(new Ingresso("ID003", evento3, "01/07/2025", 200.50, "qrCode003"));

        return lista;
    }
}
