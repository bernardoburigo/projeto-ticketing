package com.example.AppPublico.adapters;

import android.content.Context;
import android.graphics.Bitmap; // Necessário para QRCodeGenerator
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView; // Importar ImageView
import android.widget.TextView;
import android.widget.Toast; // Para feedback ao usuário

import androidx.annotation.NonNull; // Adicionar se estiver faltando
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.AppPublico.R;
import com.example.AppPublico.models.QrCodeResponseDTO;
import com.example.AppPublico.utils.QRCodeGenerator; // Assumindo que esta classe existe e funciona

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class IngressoAdapter extends RecyclerView.Adapter<IngressoAdapter.ViewHolder> {

    private final List<QrCodeResponseDTO> lista;
    private final Context context;
    private static final String TAG = "IngressoAdapter"; // Para logs

    public IngressoAdapter(List<QrCodeResponseDTO> lista, Context context) {
        this.lista = lista;
        this.context = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNome, tvValor;

        public ViewHolder(View itemView) {
            super(itemView);
            tvNome = itemView.findViewById(R.id.tvItemIngressoNomeEvento);
            tvValor = itemView.findViewById(R.id.tvItemIngressoValor);
        }
    }

    @NonNull // Adicionar
    @Override
    public IngressoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { // Adicionar @NonNull
        View v = LayoutInflater.from(context).inflate(R.layout.item_ingresso, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) { // Adicionar @NonNull
        QrCodeResponseDTO ingresso = lista.get(position);

        if (ingresso == null) {
            Log.e(TAG, "Ingresso na posição " + position + " é nulo.");
            holder.tvNome.setText("Erro: Dados indisponíveis");
            holder.tvValor.setText("");
            return;
        }

        // Usar os campos corretos do DTO (após correção do DTO)
        holder.tvNome.setText(ingresso.getNomeEvento() != null ? ingresso.getNomeEvento() : "Nome não informado");

        if (ingresso.getValor() != null) {
            holder.tvValor.setText("Valor: " + formatCurrency(ingresso.getValor()));
        } else {
            holder.tvValor.setText("Valor: N/A");
        }

        holder.itemView.setOnClickListener(v -> {
            String dadosQrCode = ingresso.getQrCode(); // Usar o campo correto do DTO
            Log.d(TAG, "Clique no item. Dados para QRCode: " + dadosQrCode);

            if (dadosQrCode != null && !dadosQrCode.isEmpty()) {
                mostrarDialogComLayoutInflado(dadosQrCode);
            } else {
                Toast.makeText(context, "Informação para QR Code indisponível.", Toast.LENGTH_SHORT).show();
                Log.w(TAG, "Campo 'qrCode' (ou 'ingressoQrCode') está nulo ou vazio para o ingresso ID: " + ingresso.getId());
            }
        });
    }

    private void mostrarDialogComLayoutInflado(String dadosParaGerarQRCode) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View dialogView = inflater.inflate(R.layout.dialog_qrcode, null); // Inflar seu layout
        builder.setView(dialogView);

        ImageView imgQrCodePopup = dialogView.findViewById(R.id.imgQrCodePopup);
        // TextView tvTituloPopup = dialogView.findViewById(R.id.tvTituloQrCodePopup); // Opcional, já tem texto no XML

        try {
            Bitmap qrBitmap = QRCodeGenerator.generateQRCode(dadosParaGerarQRCode); // Assumindo que retorna Bitmap
            if (qrBitmap != null) {
                imgQrCodePopup.setImageBitmap(qrBitmap);
            } else {
                Log.e(TAG, "Bitmap do QRCode gerado é nulo para dados: " + dadosParaGerarQRCode);
                // imgQrCodePopup.setImageResource(R.drawable.ic_error_placeholder); // Um placeholder de erro
                Toast.makeText(context, "Erro ao gerar imagem do QRCode.", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Log.e(TAG, "Erro ao gerar ou definir QRCode no ImageView: " + e.getMessage(), e);
            // imgQrCodePopup.setImageResource(R.drawable.ic_error_placeholder);
            Toast.makeText(context, "Falha ao exibir QRCode.", Toast.LENGTH_SHORT).show();
        }

        builder.setPositiveButton("Fechar", (dialog, which) -> dialog.dismiss());
        AlertDialog dialog = builder.create();
        dialog.show();
    }


    private String formatCurrency(BigDecimal valor) {
        if (valor == null) return "N/A";
        NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        return format.format(valor);
    }

    @Override
    public int getItemCount() {
        return lista != null ? lista.size() : 0; // Adicionar verificação de lista nula
    }
}