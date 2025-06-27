package com.dam.backend.lotesingressos.application.usecases;

import com.dam.backend.entities.LoteIngressoEntity;
import com.dam.backend.lotesingressos.infra.controllers.dto.response.PaginarOrBuscarLoteIngressoResponseDTO;
import com.dam.backend.lotesingressos.infra.mappers.LoteIngressoMapper;
import com.dam.backend.lotesingressos.infra.repositories.LoteIngressoRepository;
import java.util.List;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class BuscarLoteIngressoPorEventoUseCase {

    private final LoteIngressoRepository loteIngressoRepository;

    @Lazy
    public BuscarLoteIngressoPorEventoUseCase(LoteIngressoRepository loteIngressoRepository) {
        this.loteIngressoRepository = loteIngressoRepository;
    }

    public PaginarOrBuscarLoteIngressoResponseDTO buscar(Integer idEvento) {
        List<LoteIngressoEntity> loteIngresso = loteIngressoRepository.findByEvento(idEvento);

        return LoteIngressoMapper.toDTO(loteIngresso);
    }
}
