package com.dam.backend.usuarios.infra.repositories;

import com.dam.backend.entities.UsuarioEntity;
import com.dam.backend.eventos.application.gateway.UsuarioRepositoryGateway;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

@Component
public class UsuarioRepositoryGatewayImpl implements UsuarioRepositoryGateway {

    private final UsuarioRepository repository;

    public UsuarioRepositoryGatewayImpl(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<UsuarioEntity> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Optional<UsuarioEntity> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public boolean existeEmail(String email) {
        return repository.existeEmail(email);
    }

    @Override
    public Page<UsuarioEntity> findAllByAtivo(String search, PageRequest pageRequest) {
        return repository.findAllByAtivo(search, pageRequest);
    }

    @Override
    public UsuarioEntity save(UsuarioEntity usuario) {
        return repository.save(usuario);
    }

    @Override
    public void delete(UsuarioEntity usuario) {
        repository.delete(usuario);
    }
}
