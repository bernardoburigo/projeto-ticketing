package com.dam.backend.lotesingressos.infra.repositories;

import com.dam.backend.entities.TipoIngressoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoIngressoRepository extends JpaRepository<TipoIngressoEntity, Integer> {
}
