package com.dam.backend.vendas.application.usecases;

import com.dam.backend.entities.EventosEntity;
import com.dam.backend.entities.LoteIngressoEntity;
import com.dam.backend.entities.UsuarioEntity;
import com.dam.backend.entities.VendaEntity;
import com.dam.backend.entities.VendaItemEntity;
import com.dam.backend.lotesingressos.application.services.LoteIngressoService;
import com.dam.backend.shared.exceptions.EntidadeNaoEncontradaException;
import com.dam.backend.shared.utils.dto.MensagemSistema;
import com.dam.backend.vendas.application.gateways.EventoRepositoryGatewayVendas;
import com.dam.backend.vendas.application.gateways.LoteIngressoRepositoryGateway;
import com.dam.backend.vendas.application.gateways.UsuarioRepositoryGatewayVendas;
import com.dam.backend.vendas.infra.controllers.dto.request.VendaItemRequestDTO;
import com.dam.backend.vendas.infra.controllers.dto.request.VendaRequestDTO;
import com.dam.backend.vendas.infra.repositories.VendaRepository;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class VendaEventoUseCase {

    private final EventoRepositoryGatewayVendas eventoRepositoryGatewayVendas;
    private final VendaRepository vendaRepository;
    private final UsuarioRepositoryGatewayVendas usuarioRepositoryGatewayVendas;
    private final LoteIngressoRepositoryGateway loteIngressoRepositoryGateway;
    private final LoteIngressoService loteIngressoService;

    @Lazy
    public VendaEventoUseCase(EventoRepositoryGatewayVendas eventoRepositoryGatewayVendas,
                              VendaRepository vendaRepository,
                              UsuarioRepositoryGatewayVendas usuarioRepositoryGatewayVendas,
                              LoteIngressoRepositoryGateway loteIngressoRepositoryGateway,
                              LoteIngressoService loteIngressoService) {
        this.eventoRepositoryGatewayVendas = eventoRepositoryGatewayVendas;
        this.vendaRepository = vendaRepository;
        this.usuarioRepositoryGatewayVendas = usuarioRepositoryGatewayVendas;
        this.loteIngressoRepositoryGateway = loteIngressoRepositoryGateway;
        this.loteIngressoService = loteIngressoService;
    }

    public MensagemSistema vender(Integer idEvento, Integer idUsuario, VendaRequestDTO dto) {
        EventosEntity evento = buscarEvento(idEvento);
        UsuarioEntity usuario = buscarUsuario(idUsuario);
        buildVenda(evento, usuario, dto);

        return new MensagemSistema("Venda realizada com sucesso!");
    }

    private EventosEntity buscarEvento(Integer id) {
        return eventoRepositoryGatewayVendas.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Evento não encontrado."));
    }

    private UsuarioEntity buscarUsuario(Integer id) {
        return usuarioRepositoryGatewayVendas.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Usuário não encontrado."));
    }

    private LoteIngressoEntity buscarLoteIngresso(Integer id) {
        return loteIngressoRepositoryGateway.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Lote de ingresso não encontrado."));
    }

    private void buildVenda(EventosEntity evento, UsuarioEntity usuario, VendaRequestDTO dto) {
        VendaEntity venda = new VendaEntity();
        venda.setEvento(evento);
        venda.setUsuario(usuario);
        venda.setDataVenda(ZonedDateTime.now());
        venda.setPago(false);
        buildVendaItem(venda, dto);

        vendaRepository.save(venda);
    }

    private void buildVendaItem(VendaEntity venda, VendaRequestDTO dto) {
        List<VendaItemEntity> itens = new ArrayList<>();
        BigDecimal valorTotal = BigDecimal.ZERO;

        for (VendaItemRequestDTO vendaItem : dto.itens()) {
            LoteIngressoEntity loteIngresso = buscarLoteIngresso(vendaItem.idLoteIngresso());

            loteIngressoService.validarDisponibilidadeLote(loteIngresso, vendaItem.quantidade());

            VendaItemEntity item = new VendaItemEntity();
            item.setLoteIngresso(loteIngresso);
            item.setVenda(venda);
            item.setQuantidade(vendaItem.quantidade());
            item.setValorUnitario(loteIngresso.getPreco());

            itens.add(item);
            valorTotal = valorTotal.add(loteIngresso.getPreco().multiply(BigDecimal.valueOf(vendaItem.quantidade())));

            loteIngressoService.atualizarQuantidadeVendida(loteIngresso, vendaItem.quantidade());
        }

        venda.setVendaItens(itens);
        venda.setValorTotal(valorTotal);
    }
}
