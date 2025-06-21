package com.dam.backend.usuarios.application.usecases;

import com.dam.backend.entities.UsuarioEntity;
import com.dam.backend.shared.utils.PaginationUtil;
import com.dam.backend.shared.utils.dto.PaginarDTO;
import com.dam.backend.usuarios.infra.controllers.dto.response.UsuarioResponseDTO;
import com.dam.backend.usuarios.infra.mappers.UsuarioMapper;
import com.dam.backend.usuarios.infra.repositories.UsuarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class PaginarUsuarioUseCase {

    private final UsuarioRepository usuarioRepository;

    public PaginarUsuarioUseCase(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Page<UsuarioResponseDTO> paginar(PaginarDTO dto) {

        PageRequest pageRequest = PaginationUtil.paginar(dto);

        Page<UsuarioEntity> usuarios = usuarioRepository.findAllByAtivo(dto.search(), pageRequest);

        if (usuarios.isEmpty()) {
            return PaginationUtil.paginaVazia(pageRequest);
        }

        return usuarios.map(UsuarioMapper::toPaginarOrBuscar);
    }
}
