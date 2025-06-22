package com.dam.backend.eventos.infra.repositories;

import com.dam.backend.entities.CategoriaEventoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaEventoRepository extends JpaRepository<CategoriaEventoEntity, Integer> {
}
