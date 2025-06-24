package com.dam.backend.lotesingressos.application.usecases;

import com.dam.backend.entities.LoteIngressoEntity;
import com.dam.backend.lotesingressos.infra.repositories.LoteIngressoRepository;
import com.dam.backend.shared.exceptions.EntidadeNaoEncontradaException;
import com.dam.backend.shared.utils.dto.MensagemSistema;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class IndisponibilizarLoteIngressoUseCase {

    private final LoteIngressoRepository loteIngressoRepository;

    @Lazy
    public IndisponibilizarLoteIngressoUseCase(LoteIngressoRepository loteIngressoRepository) {
        this.loteIngressoRepository = loteIngressoRepository;
    }

    public MensagemSistema indisponibilizar(Integer id) {
        LoteIngressoEntity loteIngresso = loteIngressoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Lote de ingresso não encontrado."));

        loteIngresso.setAtivo(false);
        loteIngresso.setExcluido(true);
        loteIngressoRepository.save(loteIngresso);

        return new MensagemSistema("Lote indisponibilizado com sucesso!");
    }
}
