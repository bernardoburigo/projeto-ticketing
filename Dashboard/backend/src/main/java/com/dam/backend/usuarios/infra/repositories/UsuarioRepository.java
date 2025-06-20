package com.dam.backend.usuarios.infra.repositories;

import com.dam.backend.entities.UsuarioEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {

    Optional<UsuarioEntity> findByEmail(String email);

    @Query("SELECT COUNT(u) > 0 FROM UsuarioEntity u WHERE u.email = :email")
    boolean existeEmail(String email);
}
