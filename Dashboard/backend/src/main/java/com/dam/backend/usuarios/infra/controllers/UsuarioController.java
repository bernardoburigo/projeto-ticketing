package com.dam.backend.usuarios.infra.controllers;

import com.dam.backend.usuarios.application.usecases.CadastrarUsuarioUseCase;
import com.dam.backend.usuarios.application.usecases.LoginUseCase;
import com.dam.backend.usuarios.infra.controllers.dto.request.LoginRequestDTO;
import com.dam.backend.usuarios.infra.controllers.dto.request.UsuarioCadastradoRequestDTO;
import com.dam.backend.usuarios.infra.controllers.dto.response.LoginResponseDTO;
import com.dam.backend.usuarios.infra.controllers.dto.response.UsuarioCadastradoResponseDTO;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final LoginUseCase loginUseCase;
    private final CadastrarUsuarioUseCase cadastrarUsuarioUseCase;

    public UsuarioController(LoginUseCase loginUseCase,
                             CadastrarUsuarioUseCase cadastrarUsuarioUseCase) {
        this.loginUseCase = loginUseCase;
        this.cadastrarUsuarioUseCase = cadastrarUsuarioUseCase;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO dto) {
        return loginUseCase.login(dto);
    }

    @Transactional
    @PostMapping("/cadastrar")
    public ResponseEntity<UsuarioCadastradoResponseDTO> cadastrarUsuario(@RequestBody UsuarioCadastradoRequestDTO dto) {
        return cadastrarUsuarioUseCase.cadastrar(dto);
    }
}
