package com.dam.backend.usuarios.infra.controllers;

import com.dam.backend.shared.utils.dto.PaginarDTO;
import com.dam.backend.usuarios.application.usecases.CadastrarUsuarioUseCase;
import com.dam.backend.usuarios.application.usecases.LoginUseCase;
import com.dam.backend.usuarios.application.usecases.PaginarUsuarioUseCase;
import com.dam.backend.usuarios.infra.controllers.dto.request.LoginRequestDTO;
import com.dam.backend.usuarios.infra.controllers.dto.request.UsuarioCadastradoRequestDTO;
import com.dam.backend.usuarios.infra.controllers.dto.response.LoginResponseDTO;
import com.dam.backend.usuarios.infra.controllers.dto.response.PaginarUsuarioResponseDTO;
import com.dam.backend.usuarios.infra.controllers.dto.response.UsuarioCadastradoResponseDTO;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final LoginUseCase loginUseCase;
    private final CadastrarUsuarioUseCase cadastrarUsuarioUseCase;
    private final PaginarUsuarioUseCase paginarUsuarioUseCase;

    public UsuarioController(LoginUseCase loginUseCase,
                             CadastrarUsuarioUseCase cadastrarUsuarioUseCase,
                             PaginarUsuarioUseCase paginarUsuarioUseCase) {
        this.loginUseCase = loginUseCase;
        this.cadastrarUsuarioUseCase = cadastrarUsuarioUseCase;
        this.paginarUsuarioUseCase = paginarUsuarioUseCase;
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

    @GetMapping
    public ResponseEntity<Page<PaginarUsuarioResponseDTO>> paginarUsuario(@RequestParam PaginarDTO dto) {
        return ResponseEntity.ok(paginarUsuarioUseCase.paginar(dto));
    }
}
