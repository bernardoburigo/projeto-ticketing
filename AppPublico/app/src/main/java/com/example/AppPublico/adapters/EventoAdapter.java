package com.example.AppPublico.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.AppPublico.R;
import com.example.AppPublico.models.Evento;

import java.util.List;

public class EventoAdapter extends RecyclerView.Adapter<EventoAdapter.EventoViewHolder> {

    public interface OnEventoClickListener {
        void onEventoClick(Evento evento);
    }

    private List<Evento> lista;
    private OnEventoClickListener listener;

    public EventoAdapter(List<Evento> lista, OnEventoClickListener listener) {
        this.lista = lista;
        this.listener = listener;
    }

    @NonNull
    @Override
    public EventoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_evento_destaque, parent, false);
        return new EventoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventoViewHolder holder, int position) {
        Evento evento = lista.get(position);
        holder.nome.setText(evento.getNome());
        holder.local.setText(evento.getLocal());
        holder.artistas.setText(evento.getArtistas());

        holder.itemView.setOnClickListener(v -> listener.onEventoClick(evento));
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public static class EventoViewHolder extends RecyclerView.ViewHolder {
        TextView nome, local, artistas;

        public EventoViewHolder(@NonNull View itemView) {
            super(itemView);
            nome = itemView.findViewById(R.id.tvNomeEvento);
            local = itemView.findViewById(R.id.tvLocalEvento);
            artistas = itemView.findViewById(R.id.tvArtistasEvento);
        }
    }
}
