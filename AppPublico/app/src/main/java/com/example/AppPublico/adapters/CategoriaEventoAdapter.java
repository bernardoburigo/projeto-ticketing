package com.example.AppPublico.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.AppPublico.R;
import com.example.AppPublico.models.CategoriaEvento;
import com.example.AppPublico.models.Evento;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CategoriaEventoAdapter extends RecyclerView.Adapter<CategoriaEventoAdapter.CategoriaViewHolder> {

    private Context context;
    private List<CategoriaEvento> listaCategorias;
    private Map<Integer, List<Evento>> eventosPorCategoria;
    private EventoAdapter.OnEventoClickListener listener;

    public CategoriaEventoAdapter(Context context, List<CategoriaEvento> listaCategorias,
                                  Map<Integer, List<Evento>> eventosPorCategoria,
                                  EventoAdapter.OnEventoClickListener listener) {
        this.context = context;
        this.listaCategorias = listaCategorias;
        this.eventosPorCategoria = eventosPorCategoria;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CategoriaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_categoria_evento, parent, false);
        return new CategoriaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriaViewHolder holder, int position) {
        CategoriaEvento categoria = listaCategorias.get(position);
        holder.nomeCategoria.setText(categoria.getNome());

        // Busca os eventos associados à categoria pelo ID
        List<Evento> eventos = eventosPorCategoria.getOrDefault(categoria.getId(), new ArrayList<>());

        EventoAdapter eventoAdapter = new EventoAdapter(eventos, listener);
        holder.recyclerEventos.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false));
        holder.recyclerEventos.setAdapter(eventoAdapter);
    }

    @Override
    public int getItemCount() {
        return listaCategorias.size();
    }

    static class CategoriaViewHolder extends RecyclerView.ViewHolder {
        TextView nomeCategoria;
        RecyclerView recyclerEventos;

        public CategoriaViewHolder(@NonNull View itemView) {
            super(itemView);
            nomeCategoria = itemView.findViewById(R.id.tvNomeCategoria);
            recyclerEventos = itemView.findViewById(R.id.rvEventosDaCategoria);
        }
    }
}
