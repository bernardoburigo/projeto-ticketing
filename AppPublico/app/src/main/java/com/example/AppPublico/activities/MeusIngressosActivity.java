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


        adapter = new IngressoAdapter(listaMock, ingresso -> {
            Intent intent = new Intent(MeusIngressosActivity.this, DetalhesIngressoActivity.class);
            intent.putExtra("ingresso", ingresso);
            startActivity(intent);
        });

        recyclerView.setAdapter(adapter);
    }
}
