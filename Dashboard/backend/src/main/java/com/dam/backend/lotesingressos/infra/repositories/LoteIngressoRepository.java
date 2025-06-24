package com.dam.backend.lotesingressos.infra.repositories;

import com.dam.backend.entities.EventosEntity;
import com.dam.backend.entities.LoteIngressoEntity;
import com.dam.backend.entities.TipoIngressoEntity;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LoteIngressoRepository extends JpaRepository<LoteIngressoEntity, Integer> {

    List<LoteIngressoEntity> findByEventoAndTipoIngresso(EventosEntity evento, TipoIngressoEntity tipoIngresso);

    @Query("SELECT li FROM LoteIngressoEntity li " +
            "WHERE ( " +
            "  li.nome ILIKE CONCAT('%', :search, '%') OR " +
            "  li.evento.nome ILIKE CONCAT('%', :search, '%') OR " +
            "  li.tipoIngresso.nome ILIKE CONCAT('%', :search, '%') " +
            ") ")
    Page<LoteIngressoEntity> findAllByAtivo(@Param("search") String search, PageRequest pageRequest);
}
