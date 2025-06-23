package com.dam.backend.locaiseventos.infra.controllers;

import com.dam.backend.config.WithPermissoes;
import com.dam.backend.locaiseventos.application.usecases.BuscarLocalEventoUseCase;
import com.dam.backend.locaiseventos.application.usecases.CriarLocalEventoUseCase;
import com.dam.backend.locaiseventos.application.usecases.DeletarLocalEventoUseCase;
import com.dam.backend.locaiseventos.application.usecases.EditarLocalEventoUseCase;
import com.dam.backend.locaiseventos.application.usecases.PaginarLocalEventoUseCase;
import com.dam.backend.locaiseventos.infra.controllers.dto.request.LocalEventoRequestDTO;
import com.dam.backend.locaiseventos.infra.controllers.dto.response.LocaisEventoResponseDTO;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("locais-eventos")
public class LocalEnventoController {

    private final CriarLocalEventoUseCase criarLocalEventoUseCase;
    private final EditarLocalEventoUseCase editarLocalEventoUseCase;
    private final PaginarLocalEventoUseCase paginarLocalEventoUseCase;
    private final BuscarLocalEventoUseCase buscarLocalEventoUseCase;
    private final DeletarLocalEventoUseCase deletarLocalEventoUseCase;

    public LocalEnventoController(CriarLocalEventoUseCase criarLocalEventoUseCase,
                                  EditarLocalEventoUseCase editarLocalEventoUseCase,
                                  PaginarLocalEventoUseCase paginarLocalEventoUseCase,
                                  BuscarLocalEventoUseCase buscarLocalEventoUseCase,
                                  DeletarLocalEventoUseCase deletarLocalEventoUseCase) {
        this.criarLocalEventoUseCase = criarLocalEventoUseCase;
        this.editarLocalEventoUseCase = editarLocalEventoUseCase;
        this.paginarLocalEventoUseCase = paginarLocalEventoUseCase;
        this.buscarLocalEventoUseCase = buscarLocalEventoUseCase;
        this.deletarLocalEventoUseCase = deletarLocalEventoUseCase;
    }

    @WithPermissoes({Permissoes.ORGANIZADOR})
    @Transactional
    @PostMapping
    public ResponseEntity<MensagemSistema> criarLocalEvento(@RequestBody LocalEventoRequestDTO dto) {
        return ResponseEntity.ok(criarLocalEventoUseCase.criar(dto));
    }

    @WithPermissoes({Permissoes.ORGANIZADOR})
    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<MensagemSistema> editarLocalEvento(@PathVariable Integer id, @RequestBody LocalEventoRequestDTO dto) {
        return ResponseEntity.ok(editarLocalEventoUseCase.editar(id, dto));
    }

    @WithPermissoes({Permissoes.DEFAULT})
    @GetMapping
    public ResponseEntity<Page<LocaisEventoResponseDTO>> paginarLocalEvento(@ModelAttribute PaginarDTO dto) {
        PaginarDTO params = PaginarDTO.valueDefaults(
                dto.page(),
                dto.size(),
                dto.orderby(),
                dto.direction(),
                dto.search()
        );

        return ResponseEntity.ok(paginarLocalEventoUseCase.paginar(params));
    }

    @WithPermissoes({Permissoes.DEFAULT})
    @GetMapping("/{id}")
    public ResponseEntity<LocaisEventoResponseDTO> buscarLocalEvento(@PathVariable Integer id) {
        return ResponseEntity.ok(buscarLocalEventoUseCase.buscar(id));
    }

    @WithPermissoes({Permissoes.ADMIN})
    @DeleteMapping("/{id}")
    public ResponseEntity<MensagemSistema> deletarLocalEvento(@PathVariable Integer id) {
        return ResponseEntity.ok(deletarLocalEventoUseCase.deletar(id));
    }
}
