package com.dam.backend.usuarios.infra.controllers;

import com.dam.backend.config.WithPermissoes;
import com.dam.backend.shared.Permissoes;
import com.dam.backend.shared.utils.dto.MensagemSistema;
import com.dam.backend.shared.utils.dto.PaginarDTO;
import com.dam.backend.usuarios.application.usecases.AtivarOrInativarUseCase;
import com.dam.backend.usuarios.application.usecases.AtualizarUsuarioUseCase;
import com.dam.backend.usuarios.application.usecases.BuscarUsuarioUseCase;
import com.dam.backend.usuarios.application.usecases.CadastrarUsuarioUseCase;
import com.dam.backend.usuarios.application.usecases.ExcluirUsuarioUseCase;
import com.dam.backend.usuarios.application.usecases.LoginUseCase;
import com.dam.backend.usuarios.application.usecases.PaginarUsuarioUseCase;
import com.dam.backend.usuarios.infra.controllers.dto.request.AtivarInativarUsuarioRequestDTO;
import com.dam.backend.usuarios.infra.controllers.dto.request.LoginRequestDTO;
import com.dam.backend.usuarios.infra.controllers.dto.request.UsuarioRequestDTO;
import com.dam.backend.usuarios.infra.controllers.dto.response.LoginResponseDTO;
import com.dam.backend.usuarios.infra.controllers.dto.response.UsuarioCadastradoResponseDTO;
import com.dam.backend.usuarios.infra.controllers.dto.response.UsuarioResponseDTO;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    private final AtivarOrInativarUseCase ativarOrInativarUseCase;
    private final BuscarUsuarioUseCase buscarUsuarioUseCase;
    private final ExcluirUsuarioUseCase excluirUsuarioUseCase;
    private final AtualizarUsuarioUseCase atualizarUsuarioUseCase;

    public UsuarioController(LoginUseCase loginUseCase,
                             CadastrarUsuarioUseCase cadastrarUsuarioUseCase,
                             PaginarUsuarioUseCase paginarUsuarioUseCase,
                             AtivarOrInativarUseCase ativarOrInativarUseCase,
                             BuscarUsuarioUseCase buscarUsuarioUseCase,
                             ExcluirUsuarioUseCase excluirUsuarioUseCase,
                             AtualizarUsuarioUseCase atualizarUsuarioUseCase) {
        this.loginUseCase = loginUseCase;
        this.cadastrarUsuarioUseCase = cadastrarUsuarioUseCase;
        this.paginarUsuarioUseCase = paginarUsuarioUseCase;
        this.ativarOrInativarUseCase = ativarOrInativarUseCase;
        this.buscarUsuarioUseCase = buscarUsuarioUseCase;
        this.excluirUsuarioUseCase = excluirUsuarioUseCase;
        this.atualizarUsuarioUseCase = atualizarUsuarioUseCase;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO dto) {
        return loginUseCase.login(dto);
    }

    @Transactional
    @PostMapping("/cadastrar")
    public ResponseEntity<UsuarioCadastradoResponseDTO> cadastrarUsuario(@RequestBody UsuarioRequestDTO dto) {
        return cadastrarUsuarioUseCase.cadastrar(dto);
    }

    @WithPermissoes({Permissoes.ADMIN})
    @GetMapping
    public ResponseEntity<Page<UsuarioResponseDTO>> paginarUsuario(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size,
            @RequestParam(required = false) String orderby,
            @RequestParam(required = false) String direction,
            @RequestParam(required = false) String search
    ) {
        PaginarDTO dto = PaginarDTO.valueDefaults(page, size, orderby, direction, search);
        return ResponseEntity.ok(paginarUsuarioUseCase.paginar(dto));
    }

    @WithPermissoes({Permissoes.ADMIN})
    @PutMapping("ativar/{id}")
    public ResponseEntity<MensagemSistema> ativarInativarUsuario(
            @PathVariable Integer id,
            @RequestBody AtivarInativarUsuarioRequestDTO dto) {
        return ResponseEntity.ok(ativarOrInativarUseCase.executar(id, dto));
    }

    @WithPermissoes({Permissoes.ADMIN})
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> buscarUsuario(@PathVariable Integer id) {
        return ResponseEntity.ok(buscarUsuarioUseCase.buscar(id));
    }

    @WithPermissoes({Permissoes.ADMIN})
    @DeleteMapping("/{id}")
    public ResponseEntity<MensagemSistema> excluirUsuario(@PathVariable Integer id) {
        return ResponseEntity.ok(excluirUsuarioUseCase.excluir(id));
    }

    @WithPermissoes({Permissoes.ADMIN})
    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioCadastradoResponseDTO> atualizarUsuario(
            @PathVariable Integer id,
            @RequestBody UsuarioRequestDTO dto) {
        return ResponseEntity.ok(atualizarUsuarioUseCase.atualizar(id, dto));
    }
}
