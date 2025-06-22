package com.dam.backend.usuarios.application.usecases;

import com.dam.backend.entities.UsuarioEntity;
import com.dam.backend.shared.exceptions.EntidadeNaoEncontradaException;
import com.dam.backend.shared.utils.dto.MensagemSistema;
import com.dam.backend.usuarios.infra.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class ExcluirUsuarioUseCase {

    private final UsuarioRepository usuarioRepository;

    public ExcluirUsuarioUseCase(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public MensagemSistema excluir(Integer id) {
        UsuarioEntity usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Usuario não encontrado."));

        usuario.setExcluido(true);
        usuario.setAtivo(false);
        usuarioRepository.save(usuario);

        return new MensagemSistema("Usuário excluido com sucesso!");
    }
}
