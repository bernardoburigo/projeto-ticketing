package com.dam.backend.participantes.application.usecases;

import com.dam.backend.entities.EventosEntity;
import com.dam.backend.entities.ParticipanteEntity;
import com.dam.backend.entities.VendaEntity;
import com.dam.backend.entities.VendaItemEntity;
import com.dam.backend.participantes.infra.controllers.dto.response.QrCodeResponseDTO;
import com.dam.backend.participantes.infra.mappers.ParticipanteMapper;
import com.dam.backend.participantes.infra.repositories.ParticipanteRepository;
import com.dam.backend.shared.utils.PaginationUtil;
import com.dam.backend.shared.utils.dto.PaginarDTO;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class PaginarParticipantesUseCase {

    private final ParticipanteRepository participanteRepository;

    @Lazy
    public PaginarParticipantesUseCase(ParticipanteRepository participanteRepository) {
        this.participanteRepository = participanteRepository;
    }

    public Page<QrCodeResponseDTO> paginar(Integer idUsuario, PaginarDTO dto) {
        PageRequest pageRequest = PaginationUtil.paginar(dto);

        Page<ParticipanteEntity> participantes = participanteRepository.paginarPorUsuario(dto.search(), idUsuario, pageRequest);

        if (participantes.isEmpty()) {
            return PaginationUtil.paginaVazia(pageRequest);
        }

        return participantes.map(participante -> {
            VendaItemEntity item = participante.getVendaItem();
            VendaEntity venda = item.getVenda();
            EventosEntity evento = venda.getEvento();

            return ParticipanteMapper.toDTO(participante.getId(), participante.getIngressoQrCode(), evento.getNome(), venda.getValorTotal());
        });
    }
}
