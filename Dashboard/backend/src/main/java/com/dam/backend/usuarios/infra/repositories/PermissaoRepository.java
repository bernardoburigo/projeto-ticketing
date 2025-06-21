package com.dam.backend.usuarios.infra.repositories;

import com.dam.backend.entities.PermissaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissaoRepository extends JpaRepository<PermissaoEntity, Integer> {
}
