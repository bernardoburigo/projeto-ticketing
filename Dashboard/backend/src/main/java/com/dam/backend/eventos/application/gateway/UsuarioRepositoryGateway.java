package com.dam.backend.eventos.application.gateway;

import com.dam.backend.entities.UsuarioEntity;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface UsuarioRepositoryGateway {

    Optional<UsuarioEntity> findById(Integer id);

    Optional<UsuarioEntity> findByEmail(String email);

    boolean existeEmail(String email);

    Page<UsuarioEntity> findAllByAtivo(String search, PageRequest pageRequest);

    UsuarioEntity save(UsuarioEntity usuario);

    void delete(UsuarioEntity usuario);
}
