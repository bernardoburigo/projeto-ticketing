package com.dam.backend.locaiseventos.infra.repositories;

import com.dam.backend.entities.LocalEventoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LocaisEventosRepository extends JpaRepository<LocalEventoEntity, Integer> {

    @Query("SELECT le FROM LocalEventoEntity le " +
            "WHERE ( " +
            "  le.nome ILIKE CONCAT('%', :search, '%') OR " +
            "  le.cidade ILIKE CONCAT('%', :search, '%') OR " +
            "  le.estado ILIKE CONCAT('%', :search, '%') OR " +
            "  le.cep ILIKE CONCAT('%', :search, '%') " +
            ") " +
            "AND le.excluido IS FALSE")
    Page<LocalEventoEntity> findAllByAtivo(@Param("search") String search, PageRequest pageRequest);
}
