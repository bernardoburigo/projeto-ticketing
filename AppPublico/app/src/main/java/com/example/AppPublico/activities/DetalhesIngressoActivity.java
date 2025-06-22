package com.example.AppPublico.activities;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.AppPublico.R;
import com.example.AppPublico.models.Ingresso;
import com.example.AppPublico.utils.QRCodeGenerator;

public class DetalhesIngressoActivity extends AppCompatActivity {

    public static final String EXTRA_INGRESSO = "extra_ingresso";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_ingresso);

        TextView tvNomeEvento = findViewById(R.id.tvNomeEvento);
        TextView tvDetalhesEvento = findViewById(R.id.tvDetalhesEvento);
        TextView tvTipoIngresso = findViewById(R.id.tvTipoIngresso);
        TextView tvValorPago = findViewById(R.id.tvValorPago);
        TextView tvDataCompra = findViewById(R.id.tvDataCompra);
        TextView tvCodigoIngresso = findViewById(R.id.tvCodigoIngresso);
        ImageView imgQrCode = findViewById(R.id.imgQrCode);

        Ingresso ingresso = (Ingresso) getIntent().getSerializableExtra(EXTRA_INGRESSO);
        if (ingresso != null) {
            tvNomeEvento.setText(ingresso.getEvento().getNome());
            tvDetalhesEvento.setText(ingresso.getEvento().getLocal() + " - " + ingresso.getEvento().getData() + " às " + ingresso.getEvento().getHora());
            tvTipoIngresso.setText("Tipo: " + ingresso.getEvento().getTipo());
            tvValorPago.setText("Valor Pago: R$ " + ingresso.getValorPago());
            tvDataCompra.setText("Data da Compra: " + ingresso.getDataCompra());
            tvCodigoIngresso.setText("Código: " + ingresso.getIdIngresso());

            Bitmap qrBitmap = QRCodeGenerator.gerarQRCode(ingresso.getQrCode());
            if (qrBitmap != null) {
                imgQrCode.setImageBitmap(qrBitmap);
            }
        }
    }
}
