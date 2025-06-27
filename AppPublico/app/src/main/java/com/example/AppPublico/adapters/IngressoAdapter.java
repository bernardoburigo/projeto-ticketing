package com.example.AppPublico.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.AppPublico.R;
import com.example.AppPublico.activities.DetalhesIngressoActivity;
import com.example.AppPublico.models.Ingresso;

import java.util.List;

public class IngressoAdapter extends RecyclerView.Adapter<IngressoAdapter.ViewHolder> {

    public interface OnIngressoClickListener {
        void onClick(Ingresso ingresso);
    }

    private List<Ingresso> lista;
    private OnIngressoClickListener listener;

    public IngressoAdapter(List<Ingresso> lista, OnIngressoClickListener listener) {
        this.lista = lista;
        this.listener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textNomeEvento, textDataEvento, textLocalEvento, textHoraEvento, textValorPago;

        public ViewHolder(View itemView, OnIngressoClickListener listener, List<Ingresso> lista) {
            super(itemView);
            textNomeEvento = itemView.findViewById(R.id.textNomeEvento);
            textDataEvento = itemView.findViewById(R.id.textDataEvento);
            textLocalEvento = itemView.findViewById(R.id.textLocalEvento);
            textHoraEvento = itemView.findViewById(R.id.textHoraEvento);
            textValorPago = itemView.findViewById(R.id.textValorPago);

            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION && listener != null) {
                    listener.onClick(lista.get(position));
                }
            });
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_historico_ingresso, parent, false);
        return new ViewHolder(view, listener, lista);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Ingresso ingresso = lista.get(position);
        holder.textNomeEvento.setText(ingresso.getEvento().getNome());
        //holder.textDataEvento.setText(ingresso.getEvento().getData());
        //holder.textLocalEvento.setText(ingresso.getEvento().getLocal());
        //holder.textHoraEvento.setText(ingresso.getEvento().getHora());
        holder.textValorPago.setText("R$ " + String.format("%.2f", ingresso.getValorPago()));
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }
}
