package com.dam.backend.lotesingressos.application.services;

import com.dam.backend.entities.EventosEntity;
import com.dam.backend.entities.LoteIngressoEntity;
import com.dam.backend.entities.TipoIngressoEntity;
import com.dam.backend.lotesingressos.application.gateways.EventoRepositoryGateway;
import com.dam.backend.lotesingressos.infra.controllers.dto.request.LoteIngressoRequestDTO;
import com.dam.backend.lotesingressos.infra.repositories.LoteIngressoRepository;
import com.dam.backend.lotesingressos.infra.repositories.TipoIngressoRepository;
import com.dam.backend.shared.exceptions.ModelException;
import com.dam.backend.shared.utils.ConstraintsUtil;
import com.dam.backend.shared.utils.FormateDateUtil;
import com.dam.backend.shared.utils.dto.MensagemSistema;
import java.math.BigDecimal;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class LoteIngressoService {

    private static final Integer MAXIMO_LOTES_PERMITIDOS = 5;
    private static final Integer VALOR_INICIAL_QUNATIDADE_VENDIDA = 0;
    private static final Integer PERIODO_INICIO_VENDA = 15;

    private static final BigDecimal PERCENTUAL_PADRAO = BigDecimal.valueOf(5);

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

    public MensagemSistema criar(LoteIngressoRequestDTO dto) {
        EventosEntity evento = eventoRepositoryGateway.findByIdAndAtivo(dto.evento());
        ConstraintsUtil.requireNonNull(evento, "Deve ser adicionado um evento existente e ativo");

        TipoIngressoEntity tipoIngresso = tipoIngressoRepository.findById(dto.tipoIngresso())
                .orElseThrow(() -> new ModelException("Deve ser adicionado um tipo de ingresso existente"));

        validarEntradas(dto, evento, tipoIngresso);

        BigDecimal precoAtual = dto.precoInicial();

        for (int i = 0; i < dto.quantidadeLotes(); i++) {
            Integer numeroLote = i + 1;
            String nomeLote = numeroLote + "º Lote";
            Integer quantidade = dto.quantidadesPorLote().get(i);

            LoteIngressoEntity lote = new LoteIngressoEntity();
            lote.setEvento(evento);
            lote.setTipoIngresso(tipoIngresso);
            lote.setNumeroLote(numeroLote);
            lote.setNome(nomeLote);
            lote.setPreco(precoAtual);
            lote.setQuantidadeTotal(quantidade);
            lote.setQuantidadeVendida(VALOR_INICIAL_QUNATIDADE_VENDIDA);
            lote.setDataInicioVenda(dto.dataInicioVenda());
            lote.setDataFinalVenda(dto.dataFinalVenda());

            loteIngressoRepository.save(lote);

            BigDecimal percentual = dto.percentualAumento() != null ? dto.percentualAumento() : PERCENTUAL_PADRAO;

            BigDecimal aumento = precoAtual.multiply(percentual.divide(BigDecimal.valueOf(100)));
            precoAtual = precoAtual.add(aumento);
        }

        return new MensagemSistema("Lote criado com sucesso!");
    }

//    public DetalhesLoteIngressoResponseDTO editar(Integer id, LoteIngressoRequestDTO dto) {
//        LoteIngressoEntity loteIngresso = loteIngressoRepository.findById(id)
//                .orElseThrow(() -> new EntidadeNaoEncontradaException("Lote de ingresso não encontrado."));
//
//        EventosEntity evento = eventoRepositoryGateway.findByIdAndAtivo(dto.evento());
//        ConstraintsUtil.requireNonNull(evento, "Deve ser adicionado um evento existente e ativo");
//
//        TipoIngressoEntity tipoIngresso = tipoIngressoRepository.findById(dto.tipoIngresso())
//                .orElseThrow(() -> new ModelException("Deve ser adicionado um tipo de ingresso existente"));
//
//        validarEntradas(dto, evento, tipoIngresso);
//
//        loteIndisponivel(loteIngresso.isAtivo(), loteIngresso.isExcluido());
//
//        loteIngresso.setNome();
//    }

    public void validarDisponibilidadeLote(LoteIngressoEntity lote, Integer quantidadeSolicitada) {
        int disponivel = lote.getQuantidadeTotal() - lote.getQuantidadeVendida();

        if (quantidadeSolicitada > disponivel) {
            throw new ModelException("Quantidade solicitada possui mais que o disponivel.");
        }
    }

    public void atualizarQuantidadeVendida(LoteIngressoEntity lote, Integer quantidadeSolicitada) {
        lote.setQuantidadeVendida(lote.getQuantidadeVendida() + quantidadeSolicitada);
        loteIngressoRepository.save(lote);
    }

    private void loteIndisponivel(boolean ativo, boolean excluido) {
        if (!ativo || excluido) {
            throw new ModelException("Não pode editar um lote indisponivel");
        }
    }

    private void validarEntradas(LoteIngressoRequestDTO dto, EventosEntity evento, TipoIngressoEntity tipoIngresso) {

        if (dto.quantidadeLotes() == null || dto.quantidadeLotes() <= 0) {
            throw new ModelException("A quantidade de lotes deve ser maior que zero.");
        }

        if (dto.quantidadeLotes() > MAXIMO_LOTES_PERMITIDOS) {
            throw new ModelException("O máximo permitido são " + MAXIMO_LOTES_PERMITIDOS + " lotes.");
        }

        if (dto.quantidadesPorLote() == null || dto.quantidadesPorLote().size() != dto.quantidadeLotes()) {
            throw new ModelException("É obrigatório informar as quantidades de todos os lotes.");
        }

        if (dto.precoInicial() == null || dto.precoInicial().compareTo(BigDecimal.ZERO) <= 0) {
            throw new ModelException("O preço inicial deve ser maior que zero.");
        }

        if (dto.percentualAumento() == null || dto.percentualAumento().compareTo(BigDecimal.ZERO) < 0) {
            throw new ModelException("O percentual de aumento deve ser zero ou maior.");
        }

        if (dto.dataInicioVenda() == null || dto.dataFinalVenda() == null) {
            throw new ModelException("Data de início e fim de venda não podem ser nulas.");
        }

        if (dto.dataInicioVenda().isAfter(dto.dataFinalVenda())) {
            throw new ModelException(String.format(
                    "A data de início da venda %s não pode ser após a data final %s.",
                    FormateDateUtil.formatarDataZonedDateTime(dto.dataInicioVenda()),
                    FormateDateUtil.formatarDataZonedDateTime(dto.dataFinalVenda())
            ));
        }

        if (dto.dataInicioVenda().isBefore(evento.getDataInicio().minusDays(PERIODO_INICIO_VENDA))) {
            throw new ModelException(String.format(
                    "A data de início da venda %s não pode ser mais de 15 dias antes do início do evento %s.",
                    FormateDateUtil.formatarDataZonedDateTime(dto.dataInicioVenda()),
                    FormateDateUtil.formatarDataZonedDateTime(evento.getDataInicio())
            ));
        }

        if (dto.dataFinalVenda().isAfter(evento.getDataFinal())) {
            throw new ModelException(String.format(
                    "A data final da venda %s não pode ser após o término do evento %s.",
                    FormateDateUtil.formatarDataZonedDateTime(dto.dataFinalVenda()),
                    FormateDateUtil.formatarDataZonedDateTime(evento.getDataFinal())
            ));
        }
    }
}
