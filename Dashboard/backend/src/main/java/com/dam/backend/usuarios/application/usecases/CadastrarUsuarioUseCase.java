package com.dam.backend.usuarios.application.usecases;

import com.dam.backend.entities.RoleEntity;
import com.dam.backend.entities.UsuarioEntity;
import com.dam.backend.shared.enums.RolesEnum;
import com.dam.backend.shared.exceptions.ModelException;
import com.dam.backend.shared.utils.ConstraintsUtil;
import com.dam.backend.usuarios.infra.controllers.dto.request.UsuarioRequestDTO;
import com.dam.backend.usuarios.infra.controllers.dto.response.UsuarioCadastradoResponseDTO;
import com.dam.backend.usuarios.infra.mappers.UsuarioMapper;
import com.dam.backend.usuarios.infra.repositories.RoleRepository;
import com.dam.backend.usuarios.infra.repositories.UsuarioRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CadastrarUsuarioUseCase {

    private final UsuarioRepository usuarioRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public CadastrarUsuarioUseCase(UsuarioRepository usuarioRepository,
                                   RoleRepository roleRepository,
                                   BCryptPasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseEntity<UsuarioCadastradoResponseDTO> cadastrar(UsuarioRequestDTO dto) {
        validarEntradas(dto);

        Integer roleId = dto.role() != null ? dto.role() : RolesEnum.ROLE_USUARIO.getId();

        RoleEntity role = roleRepository.findByNome(RolesEnum.getRole(roleId));

        boolean existeEmail = usuarioRepository.existeEmail(dto.email().trim().toLowerCase());
        ConstraintsUtil.mustBeFalse(existeEmail, "E-mail já existe.");

        UsuarioEntity usuario = criarUsuario(dto, role);

        return ResponseEntity.ok(UsuarioMapper.toDTO(usuario));
    }

    private UsuarioEntity criarUsuario(UsuarioRequestDTO dto, RoleEntity role) {
        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email().trim().toLowerCase());
        usuario.setSenha(passwordEncoder.encode(dto.senha()));
        usuario.setRole(role);
        return usuarioRepository.save(usuario);
    }

    private void validarEntradas(UsuarioRequestDTO dto) {
        if (StringUtils.isBlank(dto.email())) {
            throw new ModelException("E-mail não pode ser nulo ou vazio");
        }

        if (StringUtils.isBlank(dto.nome())) {
            throw new ModelException("Nome não pode ser nulo ou vazio");
        }

        if (StringUtils.isBlank(dto.senha())) {
            throw new ModelException("Senha não pode ser nula ou vazia");
        }
    }
}
