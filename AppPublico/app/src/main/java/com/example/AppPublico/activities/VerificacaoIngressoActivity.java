package com.example.AppPublico.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.AppPublico.R;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.example.AppPublico.utils.SincronizadorIngressos;

import java.util.HashSet;
import java.util.Set;

public class VerificacaoIngressoActivity extends AppCompatActivity {

    private TextView tvResultado, tvNomeEvento, tvLocalEvento, tvDataHoraEvento, tvValorPago;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verificacao_ingresso);

        Button btnScan = findViewById(R.id.btnScan);
        tvResultado = findViewById(R.id.tvResultado);
        tvNomeEvento = findViewById(R.id.tvNomeEvento);
        tvLocalEvento = findViewById(R.id.tvLocalEvento);
        tvDataHoraEvento = findViewById(R.id.tvDataHoraEvento);
        tvValorPago = findViewById(R.id.tvValorPago);

        preferences = getSharedPreferences("IngressosValidados", MODE_PRIVATE);

        btnScan.setOnClickListener(v -> iniciarLeituraQRCode());
    }

    @Override
    protected void onResume() {
        super.onResume();
        SincronizadorIngressos.tentarSincronizar(this);
    }

    private void iniciarLeituraQRCode() {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setPrompt("Aponte a câmera para o QR Code do ingresso");
        integrator.setOrientationLocked(false);
        integrator.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if (result != null && result.getContents() != null) {
            String codigo = result.getContents();

            boolean jaValidado = isIngressoJaValidado(codigo);

            if (!jaValidado) salvarIngressoValidado(codigo);

            mostrarDetalhesSimulados(codigo, jaValidado);
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private boolean isIngressoJaValidado(String codigo) {
        Set<String> validados = preferences.getStringSet("validados", new HashSet<>());
        return validados.contains(codigo);
    }

    private void salvarIngressoValidado(String codigo) {
        Set<String> validados = preferences.getStringSet("validados", new HashSet<>());
        validados = new HashSet<>(validados);
        validados.add(codigo);
        preferences.edit().putStringSet("validados", validados).apply();
    }

    private void mostrarDetalhesSimulados(String qrCode, boolean jaValidado) {
        // Mock com base no QRCode (SUBSTITUIR POR LÓGICA)
        String nomeEvento = "Evento Desconhecido";
        String local = "Local não identificado";
        String data = "Data não disponível";
        String hora = "Hora desconhecida";
        String valor = "R$ 0,00";

        if (qrCode.equals("qrCode001")) {
            nomeEvento = "Tech Summit 2025";
            local = "Rua da Tecnologia, 123";
            data = "01/07/2025";
            hora = "10:00";
            valor = "R$ 40,00";
        } else if (qrCode.equals("qrCode002")) {
            nomeEvento = "Festival de Música Indie";
            local = "Parque Central";
            data = "15/08/2025";
            hora = "20:00";
            valor = "R$ 80,00";
        } else if (qrCode.equals("qrCode003")) {
            nomeEvento = "Seminário de IA";
            local = "Auditório UF";
            data = "22/09/2025";
            hora = "14:00";
            valor = "R$ 200,50";
        }

        tvResultado.setText(jaValidado ?
                "Ingresso já havia sido validado." :
                "Ingresso validado com sucesso (modo offline)");

        tvNomeEvento.setText("Evento: " + nomeEvento);
        tvLocalEvento.setText("Local: " + local);
        tvDataHoraEvento.setText("Data e Hora: " + data + " às " + hora);
        tvValorPago.setText("Valor Pago: " + valor);
    }
}
