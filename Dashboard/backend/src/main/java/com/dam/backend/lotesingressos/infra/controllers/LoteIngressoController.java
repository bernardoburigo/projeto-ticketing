package com.dam.backend.lotesingressos.infra.controllers;

import com.dam.backend.config.WithPermissoes;
import com.dam.backend.lotesingressos.application.services.LoteIngressoService;
import com.dam.backend.lotesingressos.application.usecases.BuscarLoteIngressoPorEventoUseCase;
import com.dam.backend.lotesingressos.application.usecases.BuscarLotesIngressoPorEventoAndTipoIngressoUseCase;
import com.dam.backend.lotesingressos.application.usecases.BuscarLotesIngressosUseCase;
import com.dam.backend.lotesingressos.application.usecases.IndisponibilizarLoteIngressoUseCase;
import com.dam.backend.lotesingressos.application.usecases.PaginarLoteIngressoUseCase;
import com.dam.backend.lotesingressos.infra.controllers.dto.request.LoteIngressoRequestDTO;
import com.dam.backend.lotesingressos.infra.controllers.dto.response.DetalhesLoteIngressoResponseDTO;
import com.dam.backend.lotesingressos.infra.controllers.dto.response.PaginarOrBuscarLoteIngressoResponseDTO;
import com.dam.backend.shared.Permissoes;
import com.dam.backend.shared.utils.dto.MensagemSistema;
import com.dam.backend.shared.utils.dto.PaginarDTO;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lotes-ingressos")
public class LoteIngressoController {

    private final LoteIngressoService loteIngressoService;
    private final PaginarLoteIngressoUseCase paginarLoteIngressoUseCase;
    private final BuscarLotesIngressoPorEventoAndTipoIngressoUseCase buscarLotesIngressoPorEventoAndTipoIngressoUseCase;
    private final BuscarLotesIngressosUseCase buscarLotesIngressosUseCase;
    private final IndisponibilizarLoteIngressoUseCase indisponibilizarLoteIngressoUseCase;
    private final BuscarLoteIngressoPorEventoUseCase buscarLoteIngressoPorEventoUseCase;

    public LoteIngressoController(LoteIngressoService loteIngressoService,
                                  PaginarLoteIngressoUseCase paginarLoteIngressoUseCase,
                                  BuscarLotesIngressoPorEventoAndTipoIngressoUseCase buscarLotesIngressoPorEventoAndTipoIngressoUseCase,
                                  BuscarLotesIngressosUseCase buscarLotesIngressosUseCase,
                                  IndisponibilizarLoteIngressoUseCase indisponibilizarLoteIngressoUseCase,
                                  BuscarLoteIngressoPorEventoUseCase buscarLoteIngressoPorEventoUseCase) {
        this.loteIngressoService = loteIngressoService;
        this.paginarLoteIngressoUseCase = paginarLoteIngressoUseCase;
        this.buscarLotesIngressoPorEventoAndTipoIngressoUseCase = buscarLotesIngressoPorEventoAndTipoIngressoUseCase;
        this.buscarLotesIngressosUseCase = buscarLotesIngressosUseCase;
        this.indisponibilizarLoteIngressoUseCase = indisponibilizarLoteIngressoUseCase;
        this.buscarLoteIngressoPorEventoUseCase = buscarLoteIngressoPorEventoUseCase;
    }

    @WithPermissoes({Permissoes.ORGANIZADOR})
    @Transactional
    @PostMapping
    public ResponseEntity<MensagemSistema> criarLoteIngresso(@RequestBody LoteIngressoRequestDTO dto) {
        return ResponseEntity.ok(loteIngressoService.criar(dto));
    }

    @WithPermissoes({Permissoes.ORGANIZADOR})
    @GetMapping
    public ResponseEntity<Page<PaginarOrBuscarLoteIngressoResponseDTO>> paginarLoteIngresso(@ModelAttribute PaginarDTO dto) {
        PaginarDTO params = PaginarDTO.valueDefaults(
                dto.page(),
                dto.size(),
                dto.orderby(),
                dto.direction(),
                dto.search()
        );

        return ResponseEntity.ok(paginarLoteIngressoUseCase.paginar(params));
    }

    @WithPermissoes({Permissoes.ORGANIZADOR})
    @GetMapping("lotes/evento/{idEvento}/{idTipoIngresso}")
    public ResponseEntity<PaginarOrBuscarLoteIngressoResponseDTO> buscarLotesIngresso(@PathVariable Integer idEvento,
                                                                                      @PathVariable Integer idTipoIngresso) {
        return ResponseEntity.ok(buscarLotesIngressoPorEventoAndTipoIngressoUseCase.buscarLotesPorEventoAndTipoIngresso(idEvento, idTipoIngresso));
    }

    @WithPermissoes({Permissoes.ORGANIZADOR})
    @GetMapping("/{id}")
    public ResponseEntity<DetalhesLoteIngressoResponseDTO> buscarLoteIngresso(@PathVariable Integer id) {
        return ResponseEntity.ok(buscarLotesIngressosUseCase.buscarLoteIngresso(id));
    }

    @WithPermissoes({Permissoes.ORGANIZADOR})
    @DeleteMapping("indisponibilizar/{id}")
    public ResponseEntity<MensagemSistema> indisponibilizarLote(@PathVariable Integer id) {
        return ResponseEntity.ok(indisponibilizarLoteIngressoUseCase.indisponibilizar(id));
    }

    @WithPermissoes({Permissoes.ORGANIZADOR})
    @GetMapping("/evento/{idEvento}")
    public ResponseEntity<PaginarOrBuscarLoteIngressoResponseDTO> buscarPorEvento(@PathVariable Integer idEvento) {
        return ResponseEntity.ok(buscarLoteIngressoPorEventoUseCase.buscar(idEvento));
    }
}
