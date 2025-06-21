package com.dam.backend.config;

import com.dam.backend.entities.RoleEntity;
import com.dam.backend.entities.UsuarioEntity;
import com.dam.backend.shared.enums.RolesEnum;
import com.dam.backend.shared.exceptions.EntidadeNaoEncontradaException;
import com.dam.backend.usuarios.infra.repositories.RoleRepository;
import com.dam.backend.usuarios.infra.repositories.UsuarioRepository;
import jakarta.transaction.Transactional;
import java.util.Optional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class AdminConfig implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public AdminConfig(RoleRepository roleRepository,
                       UsuarioRepository usuarioRepository,
                       BCryptPasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        RoleEntity role = Optional.ofNullable(roleRepository.findByNome(RolesEnum.ROLE_ADMIN.getRole()))
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Role não encontrada."));

        Optional<UsuarioEntity> usuario = usuarioRepository.findByEmail("admin@gmail.com");

        usuario.ifPresentOrElse(
                user -> {
                    System.out.println("Admin já existe");
                },
                () -> {
                    UsuarioEntity usuarioEntity = new UsuarioEntity();
                    usuarioEntity.setNome("admin");
                    usuarioEntity.setEmail("admin@gmail.com");
                    usuarioEntity.setSenha(passwordEncoder.encode("admin123"));
                    usuarioEntity.setRole(role);

                    usuarioRepository.save(usuarioEntity);
                }
        );
    }
}
