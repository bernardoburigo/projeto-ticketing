package com.dam.backend.usuarios.application.usecases;

import com.dam.backend.entities.UsuarioEntity;
import com.dam.backend.shared.exceptions.EntidadeNaoEncontradaException;
import com.dam.backend.shared.utils.ConstraintsUtil;
import com.dam.backend.shared.utils.dto.MensagemSistema;
import com.dam.backend.usuarios.infra.controllers.dto.request.AtivarInativarUsuarioRequestDTO;
import com.dam.backend.usuarios.infra.repositories.UsuarioRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class AtivarOrInativarUseCase {

    private final UsuarioRepository usuarioRepository;

    @Lazy
    public AtivarOrInativarUseCase(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public MensagemSistema executar(Integer id, AtivarInativarUsuarioRequestDTO dto) {
        validarEntrada(dto.ativo());

        UsuarioEntity usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Usuario não encontrado."));

        usuario.setAtivo(dto.ativo());
        usuarioRepository.save(usuario);

        return new MensagemSistema(formatarMensagem(dto.ativo()));
    }

    private String formatarMensagem(boolean ativo) {
        String status = ativo ? "ativado" : "inativado";

        return String.format("Usuário %s com sucesso!", status);
    }

    private void validarEntrada(boolean ativo) {
        ConstraintsUtil.requireNonNull(ativo, "Esse campo não pode ser nulo.");
    }
}
