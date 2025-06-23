package com.dam.backend.locaiseventos.application.usecases;

import com.dam.backend.entities.LocalEventoEntity;
import com.dam.backend.locaiseventos.infra.controllers.dto.response.LocaisEventoResponseDTO;
import com.dam.backend.locaiseventos.infra.mappers.LocalEventoMapper;
import com.dam.backend.locaiseventos.infra.repositories.LocaisEventosRepository;
import com.dam.backend.shared.utils.PaginationUtil;
import com.dam.backend.shared.utils.dto.PaginarDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class PaginarLocalEventoUseCase {

    private final LocaisEventosRepository locaisEventosRepository;

    public PaginarLocalEventoUseCase(LocaisEventosRepository locaisEventosRepository) {
        this.locaisEventosRepository = locaisEventosRepository;
    }

    public Page<LocaisEventoResponseDTO> paginar(PaginarDTO dto) {

        PageRequest pageRequest = PaginationUtil.paginar(dto);

        Page<LocalEventoEntity> locaisEventos = locaisEventosRepository.findAllByAtivo(dto.search(), pageRequest);

        if (locaisEventos.isEmpty()) {
            return PaginationUtil.paginaVazia(pageRequest);
        }

        return locaisEventos.map(LocalEventoMapper::toDTO);
    }
}
