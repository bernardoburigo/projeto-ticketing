package com.example.AppPublico.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.example.AppPublico.R;
import com.example.AppPublico.models.Evento;

public class DetalhesEventoActivity extends AppCompatActivity {

    private TextView tvNome, tvDescricao, tvDataInicio, tvDataFinal, tvCategoria, tvLocal;
    private ImageView imgEvento;
    private Button btnComprar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_evento);

        tvNome = findViewById(R.id.tvNomeEventoDetalhe);
        tvDescricao = findViewById(R.id.tvDescricaoEvento);
        tvDataInicio = findViewById(R.id.tvDataInicioEvento);
        tvDataFinal = findViewById(R.id.tvDataFinalEvento);
        tvCategoria = findViewById(R.id.tvCategoriaEvento);
        tvLocal = findViewById(R.id.tvLocalEventoDetalhe);
        imgEvento = findViewById(R.id.imgEventoDetalhe);
        btnComprar = findViewById(R.id.btnComprarIngresso);

        Evento evento = (Evento) getIntent().getSerializableExtra("evento");

        if (evento != null) {
            tvNome.setText(evento.getNome());
            tvDescricao.setText(evento.getDescricao());
            tvDataInicio.setText("Início: " + evento.getDataInicio());
            tvDataFinal.setText("Término: " + evento.getDataFinal());

            if (evento.getCategoria() != null) {
                tvCategoria.setText("Categoria: " + evento.getCategoria().getNome());
            } else {
                tvCategoria.setText("Categoria não informada");
            }

            if (evento.getLocalEvento() != null) {
                tvLocal.setText("Local: " + evento.getLocalEvento().getNome());
            } else {
                tvLocal.setText("Local não informado");
            }

            if (evento.getImagemNome() != null) {
                SharedPreferences prefs = getSharedPreferences("AppPrefs", MODE_PRIVATE);
                String token = prefs.getString("jwtToken", null);

                if (token == null) {
                    Log.e("GlideAuth", "Token não encontrado para carregar imagem.");
                    imgEvento.setImageResource(android.R.drawable.ic_dialog_alert);
                } else {
                    String imageUrl = "http://192.168.0.166:8080/eventos/imagens/" + evento.getImagemNome();

                    GlideUrl glideUrl = new GlideUrl(imageUrl, new LazyHeaders.Builder()
                            .addHeader("Authorization", "Bearer " + token)
                            .build());

                    Glide.with(this)
                            .load(glideUrl)
                            .error(android.R.drawable.ic_dialog_alert)
                            .listener(new com.bumptech.glide.request.RequestListener<android.graphics.drawable.Drawable>() {
                                @Override
                                public boolean onLoadFailed(@androidx.annotation.Nullable GlideException e, Object model,
                                                            Target<android.graphics.drawable.Drawable> target, boolean isFirstResource) {
                                    Log.e("GlideErro", "Erro ao carregar imagem: ", e);
                                    if (e != null) {
                                        for (Throwable t : e.getRootCauses()) {
                                            Log.e("GlideErro", "Causa raiz: ", t);
                                        }
                                    }
                                    return false;
                                }

                                @Override
                                public boolean onResourceReady(android.graphics.drawable.Drawable resource, Object model,
                                                               Target<Drawable> target,
                                                               DataSource dataSource, boolean isFirstResource) {
                                    Log.i("GlideInfo", "Imagem carregada com sucesso: " + model);
                                    return false;
                                }
                            })
                            .into(imgEvento);
                }
            } else {
                imgEvento.setImageResource(android.R.drawable.ic_dialog_alert);
            }

            btnComprar.setOnClickListener(v -> {
                Intent intent = new Intent(this, CompraIngressoActivity.class);
                intent.putExtra("eventoSelecionado", evento);
                startActivity(intent);
            });
        }
    }
}