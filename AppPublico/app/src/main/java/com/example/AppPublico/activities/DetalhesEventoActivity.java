package com.example.AppPublico.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.AppPublico.R;
import com.example.AppPublico.models.Evento;

public class DetalhesEventoActivity extends AppCompatActivity {

    private ImageView imgEvento;
    private TextView tvNomeEvento, tvDataHora, tvLocal, tvArtistas;
    private Button btnComprar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_evento);

        imgEvento = findViewById(R.id.imgEvento);
        tvNomeEvento = findViewById(R.id.tvNomeEvento);
        tvDataHora = findViewById(R.id.tvDataHora);
        tvLocal = findViewById(R.id.tvLocal);
        tvArtistas = findViewById(R.id.tvArtistas);
        btnComprar = findViewById(R.id.btnComprar);

        // Dados mockados
        Evento evento = new Evento(
                "Tech Summit 2025",
                "Rua da Tecnologia, 123",
                "01/07/2025",
                "10:00",
                R.drawable.exemplo_evento,
                "Alice, Bob, Carol"
        );

        // Preenche os dados
        imgEvento.setImageResource(evento.getImagemResId());
        tvNomeEvento.setText(evento.getNome());
        tvDataHora.setText(evento.getData() + " às " + evento.getHora());
        tvLocal.setText(evento.getLocal());
        tvArtistas.setText("Artistas/Palestrantes: " + evento.getArtistas());

        btnComprar.setOnClickListener(v -> {
            // futuramente: startActivity(new Intent(this, CompraIngressoActivity.class));
        });
    }
}
