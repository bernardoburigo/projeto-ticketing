package com.dam.backend.lotesingressos.application.usecases;

import com.dam.backend.entities.EventosEntity;
import com.dam.backend.entities.LoteIngressoEntity;
import com.dam.backend.entities.TipoIngressoEntity;
import com.dam.backend.lotesingressos.application.gateways.EventoRepositoryGateway;
import com.dam.backend.lotesingressos.infra.controllers.dto.response.PaginarOrBuscarLoteIngressoResponseDTO;
import com.dam.backend.lotesingressos.infra.mappers.LoteIngressoMapper;
import com.dam.backend.lotesingressos.infra.repositories.LoteIngressoRepository;
import com.dam.backend.lotesingressos.infra.repositories.TipoIngressoRepository;
import com.dam.backend.shared.exceptions.EntidadeNaoEncontradaException;
import java.util.List;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class BuscarLotesIngressoPorEventoAndTipoIngressoUseCase {

    private final LoteIngressoRepository loteIngressoRepository;
    private final EventoRepositoryGateway eventoRepositoryGateway;
    private final TipoIngressoRepository tipoIngressoRepository;

    @Lazy
    public BuscarLotesIngressoPorEventoAndTipoIngressoUseCase(LoteIngressoRepository loteIngressoRepository,
                                                              EventoRepositoryGateway eventoRepositoryGateway,
                                                              TipoIngressoRepository tipoIngressoRepository) {
        this.loteIngressoRepository = loteIngressoRepository;
        this.eventoRepositoryGateway = eventoRepositoryGateway;
        this.tipoIngressoRepository = tipoIngressoRepository;
    }

    public PaginarOrBuscarLoteIngressoResponseDTO buscarLotesPorEventoAndTipoIngresso(Integer idEvento, Integer idTipoIngresso) {
        EventosEntity evento = eventoRepositoryGateway.findById(idEvento)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Evento não encontrado."));

        TipoIngressoEntity tipoIngresso = tipoIngressoRepository.findById(idTipoIngresso)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Tipo do ingresso não encontrado."));

        List<LoteIngressoEntity> loteIngresso = loteIngressoRepository.findByEventoAndTipoIngresso(evento, tipoIngresso);

        return LoteIngressoMapper.toDTO(loteIngresso);
    }
}
