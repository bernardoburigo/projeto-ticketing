package com.dam.backend.eventos.infra.controllers;

import com.dam.backend.config.WithPermissoes;
import com.dam.backend.eventos.application.services.EventoService;
import com.dam.backend.eventos.infra.controllers.dto.request.EventoRequestDTO;
import com.dam.backend.eventos.infra.controllers.dto.response.EventoResponseDTO;
import com.dam.backend.shared.Permissoes;
import com.dam.backend.shared.utils.dto.PaginarDTO;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/eventos")
public class EventoController {

    private final EventoService eventoService;

    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @WithPermissoes({Permissoes.ORGANIZADOR})
    @Transactional
    @PostMapping
    public ResponseEntity<EventoResponseDTO> criarEvento(@RequestBody EventoRequestDTO dto) {
        return ResponseEntity.ok(eventoService.criar(dto));
    }

    @WithPermissoes({Permissoes.DEFAULT})
    @GetMapping
    public ResponseEntity<Page<EventoResponseDTO>> paginarEvento(@ModelAttribute PaginarDTO dto) {
        PaginarDTO params = PaginarDTO.valueDefaults(
                dto.page(),
                dto.size(),
                dto.orderby(),
                dto.direction(),
                dto.search()
        );

        return ResponseEntity.ok(eventoService.paginar(params));
    }

    @WithPermissoes({Permissoes.DEFAULT})
    @GetMapping("/{id}")
    public ResponseEntity<EventoResponseDTO> buscarEvento(@PathVariable Integer id) {
        return ResponseEntity.ok(eventoService.buscar(id));
    }
}
