package com.dam.backend.lotesingressos.infra.controllers;

import com.dam.backend.config.WithPermissoes;
import com.dam.backend.lotesingressos.application.usecases.LoteIngressoService;
import com.dam.backend.lotesingressos.infra.controllers.dto.request.LoteIngressoRequestDTO;
import com.dam.backend.lotesingressos.infra.controllers.dto.response.LoteIngressoResponseDTO;
import com.dam.backend.shared.Permissoes;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lotes-ingressos")
public class LoteIngressoController {

    private final LoteIngressoService loteIngressoService;

    public LoteIngressoController(LoteIngressoService loteIngressoService) {
        this.loteIngressoService = loteIngressoService;
    }

    @WithPermissoes({Permissoes.ORGANIZADOR})
    @Transactional
    @PostMapping
    public ResponseEntity<LoteIngressoResponseDTO> criarLoteIngresso(@RequestBody LoteIngressoRequestDTO dto) {
        return ResponseEntity.ok(loteIngressoService.criar(dto));
    }
}
