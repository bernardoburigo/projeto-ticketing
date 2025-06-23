package com.dam.backend.lotesingressos.infra.mappers;

import com.dam.backend.entities.LoteIngressoEntity;
import com.dam.backend.eventos.infra.mappers.EventoMapper;
import com.dam.backend.lotesingressos.infra.controllers.dto.response.LoteIngressoResponseDTO;
import com.dam.backend.shared.enums.StatusLoteIngressoEnum;
import com.dam.backend.shared.exceptions.ClasseNaoInstanciavelException;
import com.dam.backend.shared.utils.FormateDateUtil;

public final class LoteIngressoMapper {

    private LoteIngressoMapper() {
        throw new ClasseNaoInstanciavelException();
    }

    public static LoteIngressoResponseDTO toDTO(LoteIngressoEntity entity, Integer status) {

        return LoteIngressoResponseDTO.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .preco(entity.getPreco())
                .evento(EventoMapper.toLoteIngresso(entity.getEvento()))
                .tipoIngresso(TipoIngressoMapper.toDTO(entity.getTipoIngresso()))
                .dataInicioVenda(FormateDateUtil.formatarDataZonedDateTime(entity.getDataInicioVenda()))
                .dataFinalVenda(FormateDateUtil.formatarDataZonedDateTime(entity.getDataFinalVenda()))
                .quantidadeTotal(entity.getQuantidadeTotal())
                .quantidadeVendida(entity.getQuantidadeVendida())
                .status(StatusLoteIngressoEnum.getStatusLote(status))
                .build();
    }
}
