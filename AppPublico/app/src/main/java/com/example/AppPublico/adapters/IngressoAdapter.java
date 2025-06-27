package com.example.AppPublico.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.AppPublico.R;
import com.example.AppPublico.models.QrCodeResponseDTO;
import com.example.AppPublico.utils.QRCodeGenerator;

import java.util.List;

public class IngressoAdapter extends RecyclerView.Adapter<IngressoAdapter.ViewHolder> {

    private final List<QrCodeResponseDTO> lista;
    private final Context context;

    public IngressoAdapter(List<QrCodeResponseDTO> lista, Context context) {
        this.lista = lista;
        this.context = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNome, tvDocumento, tvStatus;

        public ViewHolder(View itemView) {
            super(itemView);
            tvNome = itemView.findViewById(R.id.tvNomeParticipante);
            tvDocumento = itemView.findViewById(R.id.tvDocumentoParticipante);
            tvStatus = itemView.findViewById(R.id.tvStatusCheckin);
        }
    }

    @Override
    public IngressoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_ingresso, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        QrCodeResponseDTO ingresso = lista.get(position);

        holder.tvNome.setText(ingresso.getNome());
        holder.tvDocumento.setText("Documento: " + ingresso.getDocumento());
        holder.tvStatus.setText(ingresso.isCheckin() ? "Check-in: Realizado" : "Check-in: Pendente");

        holder.itemView.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Ingresso QR Code");

            // Gera o QRCode
            String qrCode = ingresso.getIngressoQrCode();
            android.widget.ImageView imageView = new android.widget.ImageView(context);
            imageView.setImageBitmap(QRCodeGenerator.generateQRCode(qrCode));
            builder.setView(imageView);
            builder.setPositiveButton("Fechar", null);
            builder.show();
        });
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }
}
