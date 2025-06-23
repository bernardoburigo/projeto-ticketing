package com.dam.backend.lotesingressos.infra.repositories;

import com.dam.backend.entities.EventosEntity;
import com.dam.backend.entities.LoteIngressoEntity;
import com.dam.backend.entities.TipoIngressoEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoteIngressoRepository extends JpaRepository<LoteIngressoEntity, Integer> {

    List<LoteIngressoEntity> findByEventoAndTipoIngresso(EventosEntity evento, TipoIngressoEntity tipoIngresso);

    boolean existsByEventoAndTipoIngressoAndNome(EventosEntity evento, TipoIngressoEntity tipoIngresso, String nome);
}
