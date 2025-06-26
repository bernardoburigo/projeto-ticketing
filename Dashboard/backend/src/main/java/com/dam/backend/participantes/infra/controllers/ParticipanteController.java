package com.dam.backend.participantes.infra.controllers;

import com.dam.backend.config.WithPermissoes;
import com.dam.backend.participantes.application.usecases.BuscarParticipanteUseCase;
import com.dam.backend.participantes.application.usecases.CheckingUseCase;
import com.dam.backend.participantes.application.usecases.PaginarParticipantesUseCase;
import com.dam.backend.participantes.infra.controllers.dto.response.QrCodeResponseDTO;
import com.dam.backend.shared.Permissoes;
import com.dam.backend.shared.utils.dto.MensagemSistema;
import com.dam.backend.shared.utils.dto.PaginarDTO;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ingressos")
public class ParticipanteController {

    private final CheckingUseCase checkingUseCase;
    private final BuscarParticipanteUseCase buscarParticipanteUseCase;
    private final PaginarParticipantesUseCase paginarParticipantesUseCase;

    public ParticipanteController(CheckingUseCase checkingUseCase,
                                  BuscarParticipanteUseCase buscarParticipanteUseCase,
                                  PaginarParticipantesUseCase paginarParticipantesUseCase) {
        this.checkingUseCase = checkingUseCase;
        this.buscarParticipanteUseCase = buscarParticipanteUseCase;
        this.paginarParticipantesUseCase = paginarParticipantesUseCase;
    }

    @WithPermissoes({Permissoes.ORGANIZADOR})
    @GetMapping("/{qrCode}")
    public ResponseEntity<MensagemSistema> checking(String qrCode) {
        return ResponseEntity.ok(checkingUseCase.checking(qrCode));
    }

    @WithPermissoes({Permissoes.DEFAULT})
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<Page<QrCodeResponseDTO>> paginarParticipantes(@PathVariable Integer idUsuario, @ModelAttribute PaginarDTO dto) {
        PaginarDTO params = PaginarDTO.valueDefaults(dto);

        return ResponseEntity.ok(paginarParticipantesUseCase.paginar(idUsuario, params));
    }

    @WithPermissoes({Permissoes.DEFAULT})
    @GetMapping("/usuario/idUsuario/participante/{qrCode}")
    public ResponseEntity<QrCodeResponseDTO> buscarPorQrCodeAndUsuario(@PathVariable Integer idUsuario,
                                                                       @PathVariable String qrCode) {
        return ResponseEntity.ok(buscarParticipanteUseCase.buscar(idUsuario, qrCode));
    }
}
