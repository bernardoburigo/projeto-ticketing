package com.dam.backend.usuarios.application.usecases;

import com.dam.backend.entities.UsuarioEntity;
import com.dam.backend.shared.exceptions.EntidadeNaoEncontradaException;
import com.dam.backend.usuarios.infra.controllers.dto.response.UsuarioResponseDTO;
import com.dam.backend.usuarios.infra.mappers.UsuarioMapper;
import com.dam.backend.usuarios.infra.repositories.UsuarioRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class BuscarUsuarioUseCase {

    private final UsuarioRepository usuarioRepository;

    @Lazy
    public BuscarUsuarioUseCase(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioResponseDTO buscar(Integer id) {
        UsuarioEntity usuario = usuarioRepository.findById(id)
                .orElseThrow(() ->  new EntidadeNaoEncontradaException("Usuário não encontrado."));

        return UsuarioMapper.toPaginarOrBuscar(usuario);
    }
}
