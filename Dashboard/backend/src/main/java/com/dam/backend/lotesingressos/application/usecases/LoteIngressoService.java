package com.dam.backend.lotesingressos.application.usecases;

import com.dam.backend.entities.EventosEntity;
import com.dam.backend.entities.LoteIngressoEntity;
import com.dam.backend.entities.TipoIngressoEntity;
import com.dam.backend.lotesingressos.application.gateways.EventoRepositoryGateway;
import com.dam.backend.lotesingressos.infra.controllers.dto.request.LoteIngressoRequestDTO;
import com.dam.backend.lotesingressos.infra.controllers.dto.response.LoteIngressoResponseDTO;
import com.dam.backend.lotesingressos.infra.mappers.LoteIngressoMapper;
import com.dam.backend.lotesingressos.infra.repositories.LoteIngressoRepository;
import com.dam.backend.lotesingressos.infra.repositories.TipoIngressoRepository;
import com.dam.backend.shared.enums.LoteIngressoEnum;
import com.dam.backend.shared.enums.StatusLoteIngressoEnum;
import com.dam.backend.shared.exceptions.ModelException;
import com.dam.backend.shared.utils.ConstraintsUtil;
import com.dam.backend.shared.utils.FormateDateUtil;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class LoteIngressoService {

    private final static Integer VALOR_QUANTIDADE_VENDIDA = 0;
    private final static Integer DIAS_PERMITIDOS_VENDA_INGRESSO = 20;

    private final LoteIngressoRepository loteIngressoRepository;
    private final EventoRepositoryGateway eventoRepositoryGateway;
    private final TipoIngressoRepository tipoIngressoRepository;

    @Lazy
    public LoteIngressoService(LoteIngressoRepository loteIngressoRepository,
                               EventoRepositoryGateway eventoRepositoryGateway,
                               TipoIngressoRepository tipoIngressoRepository) {
        this.loteIngressoRepository = loteIngressoRepository;
        this.eventoRepositoryGateway = eventoRepositoryGateway;
        this.tipoIngressoRepository = tipoIngressoRepository;
    }

    public LoteIngressoResponseDTO criar(LoteIngressoRequestDTO dto) {
        EventosEntity evento = eventoRepositoryGateway.findByIdAndAtivo(dto.evento());
        ConstraintsUtil.requireNonNull(evento, "Deve ser adicionado um evento existente e ativo");

        TipoIngressoEntity tipoIngresso = tipoIngressoRepository.findById(dto.tipoIngresso())
                .orElseThrow(() -> new ModelException("Deve ser adicionado um tipo de ingresso existente"));

        validarEntradas(dto, evento, tipoIngresso);

        Integer statusLote = validarStatusLote(dto.dataInicioVenda(), dto.dataFinalVenda(), dto.quantidadeTotal());

        LoteIngressoEntity loteIngresso = buildLoteIngresso(dto, evento, tipoIngresso);

        return LoteIngressoMapper.toDTO(loteIngresso, statusLote);
    }

    private LoteIngressoEntity buildLoteIngresso(LoteIngressoRequestDTO dto, EventosEntity evento, TipoIngressoEntity tipoIngresso) {
        LoteIngressoEntity loteIngresso = new LoteIngressoEntity();
        loteIngresso.setNome(LoteIngressoEnum.getLote(dto.nome()));
        loteIngresso.setEvento(evento);
        loteIngresso.setTipoIngresso(tipoIngresso);
        loteIngresso.setPreco(dto.preco());
        loteIngresso.setQuantidadeTotal(dto.quantidadeTotal());
        loteIngresso.setQuantidadeVendida(VALOR_QUANTIDADE_VENDIDA);
        loteIngresso.setDataInicioVenda(dto.dataInicioVenda());
        loteIngresso.setDataFinalVenda(dto.dataFinalVenda());

        return loteIngressoRepository.save(loteIngresso);
    }

    private void validarEntradas(LoteIngressoRequestDTO dto, EventosEntity evento, TipoIngressoEntity tipoIngresso) {
        if (StringUtils.isBlank(LoteIngressoEnum.getLote(dto.nome()))) {
            ConstraintsUtil.requireNonNull(LoteIngressoEnum.getLote(dto.nome()), "Nome não pode ser nulo ou vazio");
        }

        validarQuantidadeTotal(dto.quantidadeTotal());
        validarHorarios(dto.dataInicioVenda(), dto.dataFinalVenda(), evento);
        validarPreco(dto.preco());
        validarSobreposicaoDatas(dto, evento, tipoIngresso);
        validarNomeUnicoLote(evento, tipoIngresso, LoteIngressoEnum.getLote(dto.nome()));
    }

    private void validarQuantidadeTotal(Integer quantidadeTotal) {
        ConstraintsUtil.requireNonNull(quantidadeTotal, "Quantidade total não pode ser nula");
        ConstraintsUtil.mustBeFalse(quantidadeTotal < 0, "Quantidade total deve ser maior que zero");
    }

    private void validarHorarios(ZonedDateTime dataInicio, ZonedDateTime dataFim, EventosEntity evento) {
        String dataInicioFormatada = FormateDateUtil.formatarDataZonedDateTime(dataInicio);
        String dataFinalFormatada = FormateDateUtil.formatarDataZonedDateTime(dataFim);
        String dataInicialEventoFormatada = FormateDateUtil.formatarDataZonedDateTime(evento.getDataInicio());
        String dataFinalEventoFormatada = FormateDateUtil.formatarDataZonedDateTime(evento.getDataFinal());

        if (dataInicio == null || dataFim == null) {
            throw new ModelException("Data do inicio do evendo ou final não podem ser nulas");
        }

        if (dataInicio.isAfter(dataFim)) {
            throw new ModelException("A data de início do evento não pode ser anterior à data final.");
        }

        if (dataFim.isBefore(dataInicio)) {
            throw new ModelException("A data de fim do evento não pode ser anterior à data de inicio.");
        }

        if (dataInicio.isBefore(evento.getDataInicio().minusDays(DIAS_PERMITIDOS_VENDA_INGRESSO))) {
            throw new ModelException(String.format(
                    "A data de início da venda do lote %s não pode ser antes da data de início do evento %s.",
                    dataInicioFormatada, dataInicialEventoFormatada
            ));
        }

        if (dataFim.isAfter(evento.getDataFinal())) {
            throw new ModelException(String.format(
                    "A data de fim da venda do lote (%s) não pode ser após a data de fim do evento (%s).",
                    dataFinalFormatada, dataFinalEventoFormatada
            ));
        }
    }

    private void validarPreco(BigDecimal preco) {
        if (preco == null || preco.compareTo(BigDecimal.ZERO) < 0) {
            ConstraintsUtil.requireNonNull(preco, "Preço não pode ser nulo ou negativo");
        }

        ConstraintsUtil.mustBeFalse(preco.compareTo(BigDecimal.ZERO) < 0, "Preço deve ser maior que zero");
    }

    private void validarSobreposicaoDatas(LoteIngressoRequestDTO dto, EventosEntity evento, TipoIngressoEntity tipoIngresso) {
        List<LoteIngressoEntity> lotesExistentes = loteIngressoRepository
                .findByEventoAndTipoIngresso(evento, tipoIngresso);

        for (LoteIngressoEntity lote : lotesExistentes) {
            boolean sobrepoe = dto.dataInicioVenda().isBefore(lote.getDataFinalVenda()) ||
                    dto.dataInicioVenda().isEqual(lote.getDataFinalVenda());

            sobrepoe = sobrepoe &&
                    (dto.dataFinalVenda().isAfter(lote.getDataInicioVenda()) ||
                            dto.dataFinalVenda().isEqual(lote.getDataInicioVenda()));

            if (sobrepoe) {
                throw new ModelException(String.format(
                        "O período informado %s a %s se sobrepõe ao %s do evento %s com período de %s a %s.",
                        FormateDateUtil.formatarDataZonedDateTime(dto.dataInicioVenda()),
                        FormateDateUtil.formatarDataZonedDateTime(dto.dataFinalVenda()),
                        lote.getNome(),
                        evento.getNome(),
                        FormateDateUtil.formatarDataZonedDateTime(lote.getDataInicioVenda()),
                        FormateDateUtil.formatarDataZonedDateTime(lote.getDataFinalVenda())
                ));
            }
        }
    }

    private void validarNomeUnicoLote(EventosEntity evento, TipoIngressoEntity tipoIngresso, String nome) {
        boolean existe = loteIngressoRepository.existsByEventoAndTipoIngressoAndNome(evento, tipoIngresso, nome);

        if (existe) {
            throw new ModelException(String.format(
                    "Já existe um lote com o nome '%s' para este evento e tipo de ingresso.", nome
            ));
        }
    }

    private Integer validarStatusLote(ZonedDateTime dataInicio, ZonedDateTime dataFim, Integer quantidade) {
        ZonedDateTime now = ZonedDateTime.now();

        if (quantidade != null && quantidade == 0) {
            return StatusLoteIngressoEnum.ESGOTADO.getId();
        }

        if (now.isBefore(dataInicio)) {
            return StatusLoteIngressoEnum.FUTURA.getId();
        }

        if ((now.isEqual(dataInicio) || now.isAfter(dataInicio)) &&
                (now.isEqual(dataFim) || now.isBefore(dataFim)) &&
                quantidade > 0) {
            return StatusLoteIngressoEnum.DISPONIVEL.getId();
        }

        if (now.isAfter(dataFim)) {
            return StatusLoteIngressoEnum.EXPIRADO.getId();
        }

        throw new ModelException("Não foi possível determinar o status do lote.");
    }
}
