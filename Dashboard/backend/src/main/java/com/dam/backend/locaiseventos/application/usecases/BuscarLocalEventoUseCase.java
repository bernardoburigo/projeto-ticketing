package com.dam.backend.locaiseventos.application.usecases;

import com.dam.backend.entities.LocalEventoEntity;
import com.dam.backend.locaiseventos.infra.controllers.dto.response.LocaisEventoResponseDTO;
import com.dam.backend.locaiseventos.infra.mappers.LocalEventoMapper;
import com.dam.backend.locaiseventos.infra.repositories.LocaisEventosRepository;
import com.dam.backend.shared.exceptions.EntidadeNaoEncontradaException;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class BuscarLocalEventoUseCase {

    private final LocaisEventosRepository locaisEventosRepository;

    @Lazy
    public BuscarLocalEventoUseCase(LocaisEventosRepository locaisEventosRepository) {
        this.locaisEventosRepository = locaisEventosRepository;
    }

    public LocaisEventoResponseDTO buscar(Integer id) {
        LocalEventoEntity localEvento = locaisEventosRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Local do evento não encontrado."));

        return LocalEventoMapper.toDTO(localEvento);
    }
}
