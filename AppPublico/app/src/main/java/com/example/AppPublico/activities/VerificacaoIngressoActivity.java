package com.example.AppPublico.activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.AppPublico.R;

public class VerificacaoIngressoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verificacao_ingresso);

        TextView texto = findViewById(R.id.tvVerificacao);
        texto.setText("Tela de verificação de ingressos para seguranças.");
    }
}
