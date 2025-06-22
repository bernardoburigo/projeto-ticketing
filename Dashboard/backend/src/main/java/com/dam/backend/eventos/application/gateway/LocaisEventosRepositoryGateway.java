package com.dam.backend.eventos.application.gateway;

import com.dam.backend.entities.LocalEventoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocaisEventosRepositoryGateway extends JpaRepository<LocalEventoEntity, Integer> {
}
