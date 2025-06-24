package com.dam.backend.usuarios.application.usecases;

import com.dam.backend.entities.RoleEntity;
import com.dam.backend.entities.UsuarioEntity;
import com.dam.backend.shared.enums.RolesEnum;
import com.dam.backend.shared.exceptions.EntidadeNaoEncontradaException;
import com.dam.backend.shared.exceptions.ModelException;
import com.dam.backend.usuarios.infra.controllers.dto.request.UsuarioRequestDTO;
import com.dam.backend.usuarios.infra.controllers.dto.response.UsuarioCadastradoResponseDTO;
import com.dam.backend.usuarios.infra.mappers.UsuarioMapper;
import com.dam.backend.usuarios.infra.repositories.RoleRepository;
import com.dam.backend.usuarios.infra.repositories.UsuarioRepository;
import java.util.Optional;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AtualizarUsuarioUseCase {

    private final UsuarioRepository usuarioRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Lazy
    public AtualizarUsuarioUseCase(UsuarioRepository usuarioRepository,
                                   RoleRepository roleRepository,
                                   BCryptPasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UsuarioCadastradoResponseDTO atualizar(Integer id, UsuarioRequestDTO dto) {
        validarEntradas(dto);

        RoleEntity role = Optional.ofNullable(roleRepository.findByNome(RolesEnum.getRole(dto.role())))
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Role não encontrada."));

        UsuarioEntity usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Usuario não encontrado."));

        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());

        if (StringUtils.isNotBlank(dto.senha())) {
            usuario.setSenha(passwordEncoder.encode(dto.senha()));
        }

        usuario.setRole(role);

        usuarioRepository.save(usuario);

        return UsuarioMapper.toDTO(usuario);
    }

    private void validarEntradas(UsuarioRequestDTO dto) {
        if (StringUtils.isBlank(dto.email())) {
            throw new ModelException("E-mail não pode ser nulo ou vazio");
        }

        if (StringUtils.isBlank(dto.nome())) {
            throw new ModelException("Nome não pode ser nulo ou vazio");
        }
    }
}
