package com.dam.backend.eventos.infra.mappers;

import com.dam.backend.entities.CategoriaEventoEntity;
import com.dam.backend.entities.EventosEntity;
import com.dam.backend.entities.LocalEventoEntity;
import com.dam.backend.entities.UsuarioEntity;
import com.dam.backend.eventos.infra.controllers.dto.response.EventoResponseDTO;
import com.dam.backend.eventos.infra.controllers.dto.response.LoteIngressoEventoResponseDTO;
import com.dam.backend.locaiseventos.infra.mappers.LocalEventoMapper;
import com.dam.backend.shared.enums.StatusEventoEnum;
import com.dam.backend.shared.exceptions.ClasseNaoInstanciavelException;
import com.dam.backend.shared.utils.FormateDateUtil;
import com.dam.backend.usuarios.infra.mappers.UsuarioMapper;

public final class EventoMapper {

    private EventoMapper() {
        throw new ClasseNaoInstanciavelException();
    }

    public static EventoResponseDTO toDTO(
            EventosEntity evento,
            LocalEventoEntity localEvento,
            CategoriaEventoEntity categoriaEvento,
            UsuarioEntity organizador) {
        return EventoResponseDTO.builder()
                .id(evento.getId())
                .nome(evento.getNome())
                .descricao(evento.getDescricao())
                .dataInicio(FormateDateUtil.formatarDataZonedDateTime(evento.getDataInicio()))
                .dataFinal(FormateDateUtil.formatarDataZonedDateTime(evento.getDataFinal()))
                .localEvento(LocalEventoMapper.toDTO(localEvento))
                .categoria(CategoriaEventoMapper.toDTO(categoriaEvento))
                .organizador(UsuarioMapper.toEventoDTO(organizador))
                .statusEvento(StatusEventoEnum.getStatus(evento.getStatus()))
                .imagemNome("http://localhost:8080/eventos/imagens/" + evento.getImagemNome())
                .build();
    }

    public static LoteIngressoEventoResponseDTO toLoteIngresso(EventosEntity evento) {
        return LoteIngressoEventoResponseDTO.builder()
                .id(evento.getId())
                .nome(evento.getNome())
                .build();
    }
}
