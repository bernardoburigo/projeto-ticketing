package com.dam.backend.lotesingressos.infra.mappers;

import com.dam.backend.entities.LoteIngressoEntity;
import com.dam.backend.eventos.infra.mappers.EventoMapper;
import com.dam.backend.lotesingressos.infra.controllers.dto.response.DetalhesLoteIngressoResponseDTO;
import com.dam.backend.lotesingressos.infra.controllers.dto.response.PaginarLoteIngressoResponseDTO;
import com.dam.backend.shared.enums.StatusLoteIngressoEnum;
import com.dam.backend.shared.exceptions.ClasseNaoInstanciavelException;
import com.dam.backend.shared.exceptions.ModelException;
import com.dam.backend.shared.utils.FormateDateUtil;
import java.time.ZonedDateTime;
import java.util.List;

public final class LoteIngressoMapper {

    private LoteIngressoMapper() {
        throw new ClasseNaoInstanciavelException();
    }

    public static PaginarLoteIngressoResponseDTO toDTO(List<LoteIngressoEntity> lotes) {
        if (lotes.isEmpty()) {
            throw new ModelException("Nenhum lote encontrado para a paginação.");
        }

        LoteIngressoEntity primeiro = lotes.getFirst();

        return PaginarLoteIngressoResponseDTO.builder()
                .evento(EventoMapper.toLoteIngresso(primeiro.getEvento()))
                .tipoIngresso(TipoIngressoMapper.toDTO(primeiro.getTipoIngresso()))
                .lotes(lotes.stream().map(LoteIngressoMapper::mapearDetalhe).toList())
                .build();
    }

    private static DetalhesLoteIngressoResponseDTO mapearDetalhe(LoteIngressoEntity entity) {
        return DetalhesLoteIngressoResponseDTO.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .preco(entity.getPreco())
                .dataInicioVenda(FormateDateUtil.formatarDataZonedDateTime(entity.getDataInicioVenda()))
                .dataFinalVenda(FormateDateUtil.formatarDataZonedDateTime(entity.getDataFinalVenda()))
                .quantidadeTotal(entity.getQuantidadeTotal())
                .quantidadeVendida(entity.getQuantidadeVendida())
                .status(StatusLoteIngressoEnum.getStatusLote(LoteIngressoMapper.getStatusLote(entity)))
                .build();
    }

    private static Integer getStatusLote(LoteIngressoEntity entity) {
        ZonedDateTime now = ZonedDateTime.now();
        Integer quantidade = entity.getQuantidadeTotal();

        if (quantidade != null && quantidade == 0) {
            return StatusLoteIngressoEnum.ESGOTADO.getId();
        }

        if (now.isBefore(entity.getDataInicioVenda())) {
            return StatusLoteIngressoEnum.FUTURA.getId();
        }

        if ((now.isEqual(entity.getDataInicioVenda()) || now.isAfter(entity.getDataInicioVenda())) &&
                (now.isEqual(entity.getDataFinalVenda()) || now.isBefore(entity.getDataFinalVenda())) &&
                quantidade > 0) {
            return StatusLoteIngressoEnum.DISPONIVEL.getId();
        }

        if (now.isAfter(entity.getDataFinalVenda())) {
            return StatusLoteIngressoEnum.EXPIRADO.getId();
        }

        throw new ModelException("Não foi possível determinar o status do lote.");
    }
}
