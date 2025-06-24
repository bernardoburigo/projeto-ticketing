package com.dam.backend.lotesingressos.infra.controllers;

import com.dam.backend.config.WithPermissoes;
import com.dam.backend.lotesingressos.application.services.LoteIngressoService;
import com.dam.backend.lotesingressos.application.usecases.PaginarLoteIngressoUseCase;
import com.dam.backend.lotesingressos.infra.controllers.dto.request.CriarLoteIngressoRequestDTO;
import com.dam.backend.lotesingressos.infra.controllers.dto.response.PaginarLoteIngressoResponseDTO;
import com.dam.backend.shared.Permissoes;
import com.dam.backend.shared.utils.dto.MensagemSistema;
import com.dam.backend.shared.utils.dto.PaginarDTO;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lotes-ingressos")
public class LoteIngressoController {

    private final LoteIngressoService loteIngressoService;
    private final PaginarLoteIngressoUseCase paginarLoteIngressoUseCase;

    public LoteIngressoController(LoteIngressoService loteIngressoService,
                                  PaginarLoteIngressoUseCase paginarLoteIngressoUseCase) {
        this.loteIngressoService = loteIngressoService;
        this.paginarLoteIngressoUseCase = paginarLoteIngressoUseCase;
    }

    @WithPermissoes({Permissoes.ORGANIZADOR})
    @Transactional
    @PostMapping
    public ResponseEntity<MensagemSistema> criarLoteIngresso(@RequestBody CriarLoteIngressoRequestDTO dto) {
        return ResponseEntity.ok(loteIngressoService.criar(dto));
    }

    @WithPermissoes({Permissoes.ORGANIZADOR})
    @GetMapping
    public ResponseEntity<Page<PaginarLoteIngressoResponseDTO>> paginarLoteIngresso(@ModelAttribute PaginarDTO dto) {
        PaginarDTO params = PaginarDTO.valueDefaults(
                dto.page(),
                dto.size(),
                dto.orderby(),
                dto.direction(),
                dto.search()
        );

        return ResponseEntity.ok(paginarLoteIngressoUseCase.paginar(params));
    }
}
