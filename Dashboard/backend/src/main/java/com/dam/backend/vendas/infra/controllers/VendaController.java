package com.dam.backend.vendas.infra.controllers;

import com.dam.backend.config.WithPermissoes;
import com.dam.backend.shared.Permissoes;
import com.dam.backend.shared.utils.dto.MensagemSistema;
import com.dam.backend.vendas.application.usecases.VendaEventoUseCase;
import com.dam.backend.vendas.infra.controllers.dto.request.VendaRequestDTO;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vendas")
public class VendaController {

    private final VendaEventoUseCase vendaEventoUseCase;

    @Lazy
    public VendaController(VendaEventoUseCase vendaEventoUseCase) {
        this.vendaEventoUseCase = vendaEventoUseCase;
    }

    @WithPermissoes({Permissoes.USUARIO})
    @Transactional
    @PostMapping("/evento/{idEvento}/usuario/{idUsuario}")
    public ResponseEntity<MensagemSistema> venderIngresso(@PathVariable Integer idEvento,
                                                          @PathVariable Integer idUsuario,
                                                          @RequestBody VendaRequestDTO dto) {
        return ResponseEntity.ok(vendaEventoUseCase.vender(idEvento, idUsuario, dto));
    }
}
