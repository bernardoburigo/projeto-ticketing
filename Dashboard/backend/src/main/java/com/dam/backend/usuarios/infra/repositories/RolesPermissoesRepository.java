package com.dam.backend.usuarios.infra.repositories;

import com.dam.backend.entities.RolePermissaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesPermissoesRepository extends JpaRepository<RolePermissaoEntity, Integer> {
}
