package com.dam.backend.eventos.infra.repositories;

import com.dam.backend.entities.EventosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventoRepository extends JpaRepository<EventosEntity, Integer> {
}
