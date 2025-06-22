package com.dam.backend.shared.providers;

import java.util.List;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component
public class UsuarioAutenticadoProvider {

    public Integer getIdUsuario() {
        return Integer.valueOf(getJwt().getSubject());
    }

    public List<String> getPermissoes() {
        return getJwt().getClaimAsStringList("permissoes");
    }

    public String getRole() {
        return getJwt().getClaimAsString("role");
    }

    private Jwt getJwt() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !(authentication.getPrincipal() instanceof Jwt)) {
            throw new RuntimeException("Usuário não autenticado");
        }
        return (Jwt) authentication.getPrincipal();
    }
}
