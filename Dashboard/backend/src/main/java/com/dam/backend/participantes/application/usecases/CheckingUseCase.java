package com.dam.backend.participantes.application.usecases;

import com.dam.backend.entities.EventosEntity;
import com.dam.backend.entities.LoteIngressoEntity;
import com.dam.backend.entities.ParticipanteEntity;
import com.dam.backend.entities.VendaEntity;
import com.dam.backend.entities.VendaItemEntity;
import com.dam.backend.participantes.infra.repositories.ParticipanteRepository;
import com.dam.backend.shared.exceptions.EntidadeNaoEncontradaException;
import com.dam.backend.shared.exceptions.ModelException;
import com.dam.backend.shared.utils.dto.MensagemSistema;
import java.time.ZonedDateTime;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class CheckingUseCase {

    private final ParticipanteRepository participanteRepository;

    public CheckingUseCase(ParticipanteRepository participanteRepository) {
        this.participanteRepository = participanteRepository;
    }

    public MensagemSistema checking(String qrCode) {
        ZonedDateTime agora = ZonedDateTime.now();
        ParticipanteEntity participante = buscarParticipante(qrCode);

        if (participante.isCheckin()) {
            throw new ModelException("Ingresso ja utilizado");
        }

        VendaItemEntity vendaItem = participante.getVendaItem();
        VendaEntity venda = vendaItem.getVenda();
        LoteIngressoEntity loteIngresso = vendaItem.getLoteIngresso();
        EventosEntity evento = venda.getEvento();

        validarEntradas(loteIngresso, evento, participante, agora);

        participante.setCheckin(true);
        participante.setDataCheckin(agora);
        participanteRepository.save(participante);

        return new MensagemSistema("Check-in realizado com sucesso!");
    }

    private void validarEntradas(LoteIngressoEntity loteIngresso,
                                 EventosEntity evento,
                                 ParticipanteEntity participante,
                                 ZonedDateTime agora) {

        if (!loteIngresso.getEvento().getId().equals(evento.getId())) {
            throw new ModelException("Ingresso não pertence a este evento");
        }

        if (agora.isBefore(loteIngresso.getDataInicioVenda()) || agora.isAfter(loteIngresso.getDataFinalVenda())) {
            throw new ModelException("Ingresso fora da validade de venda");
        }

        if (!participante.isAtivo() || participante.isExcluido()) {
            throw new ModelException("Ingresso inválido ou revogado");
        }
    }

    private ParticipanteEntity buscarParticipante(String qrCode) {
        return Optional.ofNullable(participanteRepository.findByIngressoQrCode(qrCode))
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Participnate não encontrado."));
    }
}
