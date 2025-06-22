package com.dam.backend.usuarios.infra.repositories;

import com.dam.backend.entities.UsuarioEntity;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {

    Optional<UsuarioEntity> findByEmail(String email);

    @Query("SELECT COUNT(u) > 0 FROM UsuarioEntity u WHERE u.email = :email " +
            "AND u.ativo = TRUE " +
            "AND u.excluido = FALSE")
    boolean existeEmail(String email);

    @Query("SELECT u FROM UsuarioEntity u " +
            "WHERE (u.nome ILIKE CONCAT('%', :search, '%') " +
            "OR u.email ILIKE CONCAT('%', :search, '%')) " +
            "AND u.excluido IS FALSE")
    Page<UsuarioEntity> findAllByAtivo(@Param("search") String search ,PageRequest pageRequest);
}
