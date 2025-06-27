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

    private Evento evento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_evento);

        evento = (Evento) getIntent().getSerializableExtra("evento");

        TextView tvTitulo = findViewById(R.id.tvTituloEvento);
        TextView tvLocal = findViewById(R.id.tvLocalEvento);
        TextView tvDataHora = findViewById(R.id.tvDataHoraEvento);
        TextView tvArtistas = findViewById(R.id.tvArtistasEvento);
        ImageView imgEvento = findViewById(R.id.imgEvento);
        Button btnComprar = findViewById(R.id.btnComprarIngresso);

        if (evento != null) {
            tvTitulo.setText(evento.getNome());
            //tvLocal.setText(evento.getLocal());
            //tvDataHora.setText(evento.getData() + " às " + evento.getHora());
            //tvArtistas.setText(evento.getArtistas());
            //imgEvento.setImageResource(evento.getImagemResId());

            btnComprar.setOnClickListener(v -> {
                Intent intent = new Intent(this, CompraIngressoActivity.class);
                //intent.putExtra("evento", evento);
                startActivity(intent);
            });
        }
    }
}
