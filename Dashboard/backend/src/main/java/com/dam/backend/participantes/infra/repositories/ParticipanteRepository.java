package com.dam.backend.participantes.infra.repositories;

import com.dam.backend.entities.ParticipanteEntity;
import com.dam.backend.entities.UsuarioEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipanteRepository extends JpaRepository<ParticipanteEntity, Integer> {

    ParticipanteEntity findByIngressoQrCode(String ingressoQrCode);

    ParticipanteEntity findByIngressoQrCodeAndUsuario(String ingressoQrCode, UsuarioEntity usuario);

    @Query("SELECT p FROM ParticipanteEntity p " +
            "WHERE p.usuario.id = :idUsuario AND p.excluido IS FALSE")
    Page<ParticipanteEntity> paginarPorUsuario(@Param("search") String search,
                                               @Param("idUsuario") Integer idUsuario,
                                               PageRequest pageRequest);
}
