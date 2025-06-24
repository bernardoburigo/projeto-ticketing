package com.dam.backend.eventos.infra.controllers;

import com.dam.backend.config.WithPermissoes;
import com.dam.backend.eventos.application.services.EventoService;
import com.dam.backend.eventos.infra.controllers.dto.request.EventoRequestDTO;
import com.dam.backend.eventos.infra.controllers.dto.response.EventoResponseDTO;
import com.dam.backend.shared.Permissoes;
import com.dam.backend.shared.utils.dto.PaginarDTO;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/eventos")
public class EventoController {

    private final Path caminhoImagem;

    private final EventoService eventoService;

    public EventoController(EventoService eventoService, @Value("${caminho.imagens}") String caminho) {
        this.eventoService = eventoService;
        this.caminhoImagem = Paths.get(caminho).toAbsolutePath().normalize();
    }

    @WithPermissoes({Permissoes.ORGANIZADOR})
    @Transactional
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<EventoResponseDTO> criarEvento(
            @RequestPart("dto") EventoRequestDTO dto,
            @RequestPart(value = "file", required = false) MultipartFile file) {
        return ResponseEntity.ok(eventoService.criar(dto, file));
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

    @GetMapping("imagens/{nomeImagem}")
    public ResponseEntity<UrlResource> buscarImagem(@PathVariable String nomeImagem) {
        try {
            Path imagem = caminhoImagem.resolve(nomeImagem).normalize();
            UrlResource recurso = new UrlResource(imagem.toUri());

            if (!recurso.exists()) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok()
                    .contentType(MediaTypeFactory.getMediaType(recurso).orElse(MediaType.APPLICATION_OCTET_STREAM))
                    .body(recurso);

        } catch (MalformedURLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
