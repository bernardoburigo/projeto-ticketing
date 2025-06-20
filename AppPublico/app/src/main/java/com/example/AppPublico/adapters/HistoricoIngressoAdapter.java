package com.example.AppPublico.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.AppPublico.R;
import com.example.AppPublico.models.HistoricoIngresso;

import java.util.List;

public class HistoricoIngressoAdapter extends RecyclerView.Adapter<HistoricoIngressoAdapter.ViewHolder> {

    public interface OnHistoricoClickListener {
        void onClick(HistoricoIngresso ingresso);
    }

    private List<HistoricoIngresso> lista;
    private OnHistoricoClickListener listener;

    public HistoricoIngressoAdapter(List<HistoricoIngresso> lista, OnHistoricoClickListener listener) {
        this.lista = lista;
        this.listener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textNomeEvento, textDataEvento, textLocalEvento, textHoraEvento, textValorPago;

        public ViewHolder(View itemView, OnHistoricoClickListener listener, List<HistoricoIngresso> lista) {
            super(itemView);
            textNomeEvento = itemView.findViewById(R.id.textNomeEvento);
            textDataEvento = itemView.findViewById(R.id.textDataEvento);
            textLocalEvento = itemView.findViewById(R.id.textLocalEvento);
            textHoraEvento = itemView.findViewById(R.id.textHoraEvento);
            textValorPago = itemView.findViewById(R.id.textValorPago);

            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onClick(lista.get(position));
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public HistoricoIngressoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_historico_ingresso, parent, false);
        return new ViewHolder(view, listener, lista);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HistoricoIngresso ingresso = lista.get(position);
        holder.textNomeEvento.setText(ingresso.getNomeEvento());
        holder.textDataEvento.setText(ingresso.getData());
        holder.textLocalEvento.setText(ingresso.getLocal());
        holder.textHoraEvento.setText(ingresso.getHora());
        holder.textValorPago.setText(ingresso.getValorPago());
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }
}
