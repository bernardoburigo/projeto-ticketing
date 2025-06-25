package com.dam.backend.usuarios.application.usecases;

import com.dam.backend.entities.UsuarioEntity;
import com.dam.backend.shared.utils.FormateDateUtil;
import com.dam.backend.usuarios.infra.controllers.dto.request.LoginRequestDTO;
import com.dam.backend.usuarios.infra.controllers.dto.response.LoginResponseDTO;
import com.dam.backend.usuarios.infra.controllers.exceptions.LoginIncorretoException;
import com.dam.backend.usuarios.infra.repositories.UsuarioRepository;
import com.dam.backend.entities.UsuarioEntity;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.List;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

@Service
public class LoginUseCase {

    private final JwtEncoder jwtEncoder;
    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Lazy
    public LoginUseCase(JwtEncoder jwtEncoder,
                        UsuarioRepository usuarioRepository,
                        BCryptPasswordEncoder passwordEncoder) {
        this.jwtEncoder = jwtEncoder;
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseEntity<LoginResponseDTO> login(LoginRequestDTO dto) {
        UsuarioEntity usuario = usuarioRepository.findByEmail(dto.email())
                .orElseThrow(LoginIncorretoException::new);

        if (!isLoginValid(dto.senha(), usuario.getSenha(), passwordEncoder) || usuarioInvalido(usuario)) {
            throw new LoginIncorretoException();
        }

        return ResponseEntity.ok(buildJWT(usuario));
    }

    private boolean isLoginValid(String senhaRequest, String senhaBanco, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(senhaRequest, senhaBanco);
    }

    private boolean usuarioInvalido(UsuarioEntity usuario) {
        return !usuario.isAtivo() || usuario.isExcluido();
    }

    private LoginResponseDTO buildJWT(UsuarioEntity usuario) {
        Instant now = ZonedDateTime.now().toInstant();
        int expiresIn = 28800;
        String expiresInToken = FormateDateUtil.formatarDataEHoraZonedDateTime(ZonedDateTime.now().plusSeconds(expiresIn));

        List<String> permissoes = usuario.getRole().getPermissoes().stream()
                .map(rp -> rp.getPermissao().getPermissao())
                .toList();

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("dam")
                .subject(usuario.getId().toString())
                .claim("role", usuario.getRole().getNome())
                .claim("permissoes", permissoes)
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiresIn))
                .build();

        String jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        return new LoginResponseDTO(jwtValue, expiresInToken, usuario.getNome());
    }
}
