package com.dam.backend.lotesingressos.infra.mappers;

import com.dam.backend.entities.LoteIngressoEntity;
import com.dam.backend.eventos.infra.mappers.EventoMapper;
import com.dam.backend.lotesingressos.infra.controllers.dto.response.DetalhesLoteIngressoResponseDTO;
import com.dam.backend.lotesingressos.infra.controllers.dto.response.PaginarOrBuscarLoteIngressoResponseDTO;
import com.dam.backend.lotesingressos.infra.controllers.dto.response.TipoIngressoComLotesResponseDTO;
import com.dam.backend.shared.enums.StatusLoteIngressoEnum;
import com.dam.backend.shared.exceptions.ClasseNaoInstanciavelException;
import com.dam.backend.shared.exceptions.ModelException;
import com.dam.backend.shared.utils.FormateDateUtil;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class LoteIngressoMapper {

    private LoteIngressoMapper() {
        throw new ClasseNaoInstanciavelException();
    }

    public static PaginarOrBuscarLoteIngressoResponseDTO toDTO(List<LoteIngressoEntity> lotes) {
        if (lotes.isEmpty()) {
            throw new ModelException("Nenhum lote encontrado para a paginação.");
        }

        LoteIngressoEntity primeiro = lotes.getFirst();
        var evento = EventoMapper.toLoteIngresso(primeiro.getEvento());

        Map<Integer, List<LoteIngressoEntity>> agrupadoPorTipo = lotes.stream()
                .collect(Collectors.groupingBy(l -> l.getTipoIngresso().getId()));

        List<TipoIngressoComLotesResponseDTO> tipos = agrupadoPorTipo.values().stream()
                .map(tipoLotes -> TipoIngressoComLotesResponseDTO.builder()
                        .tipoIngresso(TipoIngressoMapper.toDTO(tipoLotes.getFirst().getTipoIngresso()))
                        .lotes(tipoLotes.stream().map(LoteIngressoMapper::mapearDetalhe).toList())
                        .build())
                .toList();

        return PaginarOrBuscarLoteIngressoResponseDTO.builder()
                .evento(evento)
                .tiposIngresso(tipos)
                .build();
    }

    public static DetalhesLoteIngressoResponseDTO mapearDetalhe(LoteIngressoEntity entity) {
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

        if (!entity.isAtivo() || entity.isExcluido()) {
            return StatusLoteIngressoEnum.INDISPONIVEL.getId();
        }

        if (quantidade != null && quantidade == 0) {
            return StatusLoteIngressoEnum.ESGOTADO.getId();
        }

        if (now.isBefore(entity.getDataInicioVenda())) {
            return StatusLoteIngressoEnum.FUTURA.getId();
        }

        boolean vendaIniciada = !now.isBefore(entity.getDataInicioVenda());
        boolean vendaNaoTerminou = now.isBefore(entity.getDataFinalVenda()) || now.isEqual(entity.getDataFinalVenda());

        if (vendaIniciada && vendaNaoTerminou && (quantidade == null || quantidade > 0)) {
            return StatusLoteIngressoEnum.DISPONIVEL.getId();
        }

        if (now.isAfter(entity.getDataFinalVenda())) {
            return StatusLoteIngressoEnum.EXPIRADO.getId();
        }

        throw new ModelException("Não foi possível determinar o status do lote.");
    }
}
