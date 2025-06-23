package com.dam.backend.config;

import com.dam.backend.shared.exceptions.ModelException;
import com.dam.backend.shared.providers.UsuarioAutenticadoProvider;
import java.util.List;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PermissaoAspect {

    private final UsuarioAutenticadoProvider usuarioAutenticadoProvider;

    public PermissaoAspect(UsuarioAutenticadoProvider usuarioAutenticadoProvider) {
        this.usuarioAutenticadoProvider = usuarioAutenticadoProvider;
    }

    @Before("@annotation(withPermissoes)")
    public void verificarPermissoes(JoinPoint joinPoint, WithPermissoes withPermissoes) {
        List<String> permissoesUsuario = usuarioAutenticadoProvider.getPermissoes();

        for (String permissaoNecessaria : withPermissoes.value()) {
            if (!permissoesUsuario.contains(permissaoNecessaria)) {
                throw new ModelException(
                        "Acesso negado. Permissão necessária: " + permissaoNecessaria,
                        HttpStatus.FORBIDDEN
                );
            }
        }
    }
}
