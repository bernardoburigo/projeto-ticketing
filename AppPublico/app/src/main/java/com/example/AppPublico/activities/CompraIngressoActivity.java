package com.example.AppPublico.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.AppPublico.R;
import com.example.AppPublico.models.Evento;
import com.example.AppPublico.models.Ingresso;
import com.example.AppPublico.utils.AppPublicoSession;
import com.example.AppPublico.utils.QRCodeGenerator;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

public class CompraIngressoActivity extends AppCompatActivity {

    private Evento evento;
    private Spinner spinnerTipo;
    private EditText edtCupom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compra_ingresso);

        evento = (Evento) getIntent().getSerializableExtra("evento");

        TextView tvResumo = findViewById(R.id.tvResumoEvento);
        spinnerTipo = findViewById(R.id.spinnerTipoIngresso);
        edtCupom = findViewById(R.id.edtCupom);
        Button btnComprar = findViewById(R.id.btnConfirmarCompra);

        if (evento != null) {
            tvResumo.setText(evento.getNome() + "\n" + evento.getData() + " às " + evento.getHora());
        }

        // Tipos de ingresso
        String[] tipos = {"Pista", "VIP", "Camarote"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, tipos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTipo.setAdapter(adapter);

        btnComprar.setOnClickListener(v -> {
            String tipoSelecionado = spinnerTipo.getSelectedItem().toString();
            String cupom = edtCupom.getText().toString().trim();

            String codigoIngresso = UUID.randomUUID().toString();
            String dataAtual = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());

            // Valor fictício: definir valores reais por tipo de ingresso
            double valorBase = 100.00;
            double desconto = cupom.equalsIgnoreCase("DESCONTO10") ? 10.00 : 0.00;
            double valorPago = valorBase - desconto;

            // Gera o ingresso (com código como string para QRCode)
            Ingresso ingresso = new Ingresso(
                    codigoIngresso,
                    evento,
                    dataAtual,
                    valorPago,
                    codigoIngresso // o QRCodeGenerator gera a partir disso
            );

            if (AppPublicoSession.getIngressos().isEmpty()) {
                AppPublicoSession.setIngressos(new ArrayList<>());
            }

            AppPublicoSession.getIngressos().add(ingresso);
            Toast.makeText(this, "Compra confirmada!", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}
