package com.dam.backend.participantes.application.usecases;

import com.dam.backend.entities.EventosEntity;
import com.dam.backend.entities.ParticipanteEntity;
import com.dam.backend.entities.UsuarioEntity;
import com.dam.backend.entities.VendaEntity;
import com.dam.backend.entities.VendaItemEntity;
import com.dam.backend.participantes.infra.controllers.dto.response.QrCodeResponseDTO;
import com.dam.backend.participantes.infra.mappers.ParticipanteMapper;
import com.dam.backend.participantes.infra.repositories.ParticipanteRepository;
import com.dam.backend.shared.exceptions.EntidadeNaoEncontradaException;
import com.dam.backend.usuarios.infra.repositories.UsuarioRepository;
import java.util.Optional;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class BuscarParticipanteUseCase {

    private final ParticipanteRepository participanteRepository;
    private final UsuarioRepository usuarioRepository;

    @Lazy
    public BuscarParticipanteUseCase(ParticipanteRepository participanteRepository,
                                     UsuarioRepository usuarioRepository) {
        this.participanteRepository = participanteRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public QrCodeResponseDTO buscar(Integer idUsuario, String qrCode) {
        UsuarioEntity usuario = buscarUsuario(idUsuario);
        ParticipanteEntity participante = buscarQrCodeAndUsuario(qrCode, usuario);
        VendaItemEntity vendaItem = participante.getVendaItem();
        VendaEntity venda = vendaItem.getVenda();
        EventosEntity evento = venda.getEvento();

        return ParticipanteMapper.toDTO(
                participante.getId(),
                participante.getIngressoQrCode(),
                evento.getNome(),
                venda.getValorTotal()
        );
    }

    private UsuarioEntity buscarUsuario(Integer id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Usuário não encontrado"));
    }

    private ParticipanteEntity buscarQrCodeAndUsuario(String qrCode, UsuarioEntity usuario) {
        return Optional.ofNullable(participanteRepository.findByIngressoQrCodeAndUsuario(qrCode, usuario))
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Ingresso não encontrado"));
    }

}