package com.dam.backend.locaiseventos.infra.controllers;

import com.dam.backend.config.WithPermissoes;
import com.dam.backend.locaiseventos.application.usecases.CriarLocalEventoUseCase;
import com.dam.backend.locaiseventos.infra.controllers.dto.request.LocalEventoRequestDTO;
import com.dam.backend.shared.Permissoes;
import com.dam.backend.shared.utils.dto.MensagemSistema;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("locais-eventos")
public class LocalEnventoController {

    private final CriarLocalEventoUseCase criarLocalEventoUseCase;

    public LocalEnventoController(CriarLocalEventoUseCase criarLocalEventoUseCase) {
        this.criarLocalEventoUseCase = criarLocalEventoUseCase;
    }

    @WithPermissoes({Permissoes.ORGANIZADOR})
    @Transactional
    @PostMapping
    public ResponseEntity<MensagemSistema> criarLocalEvento(@RequestBody LocalEventoRequestDTO dto) {
        return ResponseEntity.ok(criarLocalEventoUseCase.criar(dto));
    }
}
