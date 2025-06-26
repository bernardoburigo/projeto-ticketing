package com.dam.backend.vendas.application.gateways;

import com.dam.backend.entities.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositoryGatewayVendas extends JpaRepository<UsuarioEntity, Integer> {
}
