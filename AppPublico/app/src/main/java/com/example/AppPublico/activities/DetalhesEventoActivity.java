package com.example.AppPublico.activities;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.AppPublico.R;
import com.example.AppPublico.models.Evento;

public class DetalhesEventoActivity extends AppCompatActivity {

    public static final String EXTRA_EVENTO = "extra_evento";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_evento);

        TextView tvNome = findViewById(R.id.tvNomeEvento);
        TextView tvLocal = findViewById(R.id.tvLocalEvento);
        TextView tvDataHora = findViewById(R.id.tvDataHoraEvento);
        TextView tvArtistas = findViewById(R.id.tvArtistasEvento);
        ImageView imgEvento = findViewById(R.id.imgEvento);

        Evento evento = (Evento) getIntent().getSerializableExtra(EXTRA_EVENTO);
        if (evento != null) {
            tvNome.setText(evento.getNome());
            tvLocal.setText(evento.getLocal());
            tvDataHora.setText(evento.getData() + " às " + evento.getHora());
            tvArtistas.setText(evento.getArtistas());
            imgEvento.setImageResource(evento.getImagemResId());
        }
    }
}
