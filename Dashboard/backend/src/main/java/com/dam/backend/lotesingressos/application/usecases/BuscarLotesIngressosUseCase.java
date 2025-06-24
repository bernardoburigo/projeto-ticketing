package com.dam.backend.lotesingressos.application.usecases;

import com.dam.backend.entities.LoteIngressoEntity;
import com.dam.backend.lotesingressos.infra.controllers.dto.response.DetalhesLoteIngressoResponseDTO;
import com.dam.backend.lotesingressos.infra.mappers.LoteIngressoMapper;
import com.dam.backend.lotesingressos.infra.repositories.LoteIngressoRepository;
import com.dam.backend.shared.exceptions.EntidadeNaoEncontradaException;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class BuscarLotesIngressosUseCase {

    private final LoteIngressoRepository loteIngressoRepository;

    @Lazy
    public BuscarLotesIngressosUseCase(LoteIngressoRepository loteIngressoRepository) {
        this.loteIngressoRepository = loteIngressoRepository;
    }

    public DetalhesLoteIngressoResponseDTO buscarLoteIngresso(Integer idLoteIngresso) {
        LoteIngressoEntity loteIngresso = loteIngressoRepository.findById(idLoteIngresso)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Lote de ingresso não encontrado."));

        return LoteIngressoMapper.mapearDetalhe(loteIngresso);
    }
}
