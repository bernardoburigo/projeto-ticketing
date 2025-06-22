package com.dam.backend.locaiseventos.infra.repositories;

import com.dam.backend.entities.LocalEventoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocaisEventosRepository extends JpaRepository<LocalEventoEntity, Integer> {
}
