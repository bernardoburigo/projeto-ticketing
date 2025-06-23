package com.dam.backend.eventos.infra.repositories;

import com.dam.backend.entities.EventosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EventoRepository extends JpaRepository<EventosEntity, Integer> {

    @Query("SELECT e FROM EventosEntity e WHERE e.id = :id " +
            "and e.excluido IS FALSE ")
    EventosEntity findByIdAndAtivo(@Param("id") Integer id);
}
