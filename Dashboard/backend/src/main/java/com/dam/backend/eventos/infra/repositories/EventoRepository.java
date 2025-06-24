package com.dam.backend.eventos.infra.repositories;

import com.dam.backend.entities.EventosEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EventoRepository extends JpaRepository<EventosEntity, Integer> {

    @Query("SELECT e FROM EventosEntity e WHERE e.id = :id " +
            "and e.excluido IS FALSE ")
    EventosEntity findByIdAndAtivo(@Param("id") Integer id);

    @Query("SELECT e FROM EventosEntity e " +
            "WHERE ( " +
            "  e.nome ILIKE CONCAT('%', :search, '%')" +
            ") " +
            "AND e.excluido IS FALSE")
    Page<EventosEntity> paginarPorAtivo(@Param("search") String search, PageRequest pageRequest);
}
