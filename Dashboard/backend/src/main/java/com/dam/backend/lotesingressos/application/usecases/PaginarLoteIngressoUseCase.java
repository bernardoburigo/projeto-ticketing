package com.dam.backend.lotesingressos.application.usecases;

import com.dam.backend.entities.LoteIngressoEntity;
import com.dam.backend.lotesingressos.infra.controllers.dto.response.PaginarLoteIngressoResponseDTO;
import com.dam.backend.lotesingressos.infra.mappers.LoteIngressoMapper;
import com.dam.backend.lotesingressos.infra.repositories.LoteIngressoRepository;
import com.dam.backend.shared.utils.PaginationUtil;
import com.dam.backend.shared.utils.dto.PaginarDTO;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class PaginarLoteIngressoUseCase {

    private final LoteIngressoRepository loteIngressoRepository;

    @Lazy
    public PaginarLoteIngressoUseCase(LoteIngressoRepository loteIngressoRepository) {
        this.loteIngressoRepository = loteIngressoRepository;
    }

    public Page<PaginarLoteIngressoResponseDTO> paginar(PaginarDTO dto) {
        PageRequest pageRequest = PaginationUtil.paginar(dto);

        Page<LoteIngressoEntity> loteIngresso = loteIngressoRepository.findAllByAtivo(dto.search(), pageRequest);

        if (loteIngresso.isEmpty()) {
            return PaginationUtil.paginaVazia(pageRequest);
        }

        Map<String, List<LoteIngressoEntity>> agrupado = loteIngresso.getContent().stream()
                .collect(Collectors.groupingBy(this::gerarChaveAgrupamento));

        List<PaginarLoteIngressoResponseDTO> conteudo = agrupado.values().stream()
                .map(LoteIngressoMapper::toDTO)
                .toList();

        return new PageImpl<>(conteudo, pageRequest, loteIngresso.getTotalElements());
    }

    private String gerarChaveAgrupamento(LoteIngressoEntity lote) {
        return String.format("%d-%d",
                lote.getEvento().getId(),
                lote.getTipoIngresso().getId());
    }
}
