package com.dam.backend.vendas.infra.repositories;

import com.dam.backend.entities.VendaItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendaItemRepository extends JpaRepository<VendaItemEntity, Integer> {
}
