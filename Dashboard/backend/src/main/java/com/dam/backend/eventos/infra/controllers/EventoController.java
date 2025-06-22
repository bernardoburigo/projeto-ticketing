package com.dam.backend.eventos.infra.controllers;

import com.dam.backend.config.WithPermissoes;
import com.dam.backend.eventos.application.usecases.CriarEventoUseCase;
import com.dam.backend.eventos.infra.controllers.dto.request.EventoRequestDTO;
import com.dam.backend.eventos.infra.controllers.dto.response.EventoResponseDTO;
import com.dam.backend.shared.Permissoes;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/eventos")
public class EventoController {

    private final CriarEventoUseCase criarEventoUseCase;

    public EventoController(CriarEventoUseCase criarEventoUseCase) {
        this.criarEventoUseCase = criarEventoUseCase;
    }

    @WithPermissoes({Permissoes.ORGANIZADOR})
    @Transactional
    @PostMapping
    public ResponseEntity<EventoResponseDTO> criarEvento(@RequestBody EventoRequestDTO dto) {
        return ResponseEntity.ok(criarEventoUseCase.criar(dto));
    }
}
