package com.dam.backend.eventos.application.usecases;

import com.dam.backend.entities.CategoriaEventoEntity;
import com.dam.backend.entities.EventosEntity;
import com.dam.backend.entities.LocalEventoEntity;
import com.dam.backend.entities.UsuarioEntity;
import com.dam.backend.eventos.application.gateway.LocaisEventosRepositoryGateway;
import com.dam.backend.eventos.application.gateway.UsuarioRepositoryGateway;
import com.dam.backend.eventos.infra.controllers.dto.request.EventoRequestDTO;
import com.dam.backend.eventos.infra.controllers.dto.response.EventoResponseDTO;
import com.dam.backend.eventos.infra.mappers.EventoMapper;
import com.dam.backend.eventos.infra.repositories.CategoriaEventoRepository;
import com.dam.backend.eventos.infra.repositories.EventoRepository;
import com.dam.backend.shared.enums.StatusEventoEnum;
import com.dam.backend.shared.exceptions.EntidadeNaoEncontradaException;
import com.dam.backend.shared.exceptions.ModelException;
import com.dam.backend.shared.utils.ConstraintsUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class CriarEventoUseCase {

    private final EventoRepository eventoRepository;
    private final LocaisEventosRepositoryGateway locaisEventosRepositoryGateway;
    private final UsuarioRepositoryGateway usuarioRepositoryGateway;
    private final CategoriaEventoRepository categoriaEventoRepository;

    @Lazy
    public CriarEventoUseCase(EventoRepository eventoRepository,
                              LocaisEventosRepositoryGateway locaisEventosRepositoryGateway,
                              UsuarioRepositoryGateway usuarioRepositoryGateway,
                              CategoriaEventoRepository categoriaEventoRepository) {
        this.eventoRepository = eventoRepository;
        this.locaisEventosRepositoryGateway = locaisEventosRepositoryGateway;
        this.usuarioRepositoryGateway = usuarioRepositoryGateway;
        this.categoriaEventoRepository = categoriaEventoRepository;
    }

    public EventoResponseDTO criar(EventoRequestDTO dto) {
        validarResposta(dto);

        LocalEventoEntity localEvento = buscarLocaisEventos(dto.localEvento());
        CategoriaEventoEntity categoriaEvento = buscarCategoriaEvento(dto.categoria());
        UsuarioEntity usuarioEvento = buscarUsuarioEvento(dto.organizador());
        EventosEntity evento = buildEvento(dto, localEvento, categoriaEvento, usuarioEvento);

        return EventoMapper.toDTO(evento, localEvento, categoriaEvento, usuarioEvento);
    }

    private void validarResposta(EventoRequestDTO dto) {
        if (StringUtils.isBlank(dto.nome())) {
            throw new ModelException("Nome não pode ser nulo ou vazio");
        }

        ConstraintsUtil.requireNonNull(dto.dataInicio(), "Data do inicio do evento não poder ser nula");
        ConstraintsUtil.requireNonNull(dto.dataFinal(), "Data do final do evento não poder ser nula");
        ConstraintsUtil.requireNonNull(dto.localEvento(), "Local do evento não pode ser nulo");
        ConstraintsUtil.requireNonNull(dto.categoria(), "Categoria do evento não poder ser nula");
        ConstraintsUtil.requireNonNull(dto.organizador(), "Organizador do evento não poder ser nulo");
    }

    private LocalEventoEntity buscarLocaisEventos(Integer id) {
        return locaisEventosRepositoryGateway.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Local de evento não encontrado."));
    }

    private CategoriaEventoEntity buscarCategoriaEvento(Integer id) {
        return categoriaEventoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("categoria do evento não encontrado."));
    }

    private UsuarioEntity buscarUsuarioEvento(Integer id) {
        return usuarioRepositoryGateway.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("categoria do evento não encontrado."));
    }

    private EventosEntity buildEvento(
            EventoRequestDTO dto,
            LocalEventoEntity localEvento,
            CategoriaEventoEntity categoriaEvento,
            UsuarioEntity organizadorEvento) {
        EventosEntity evento = new EventosEntity();
        evento.setNome(dto.nome());
        evento.setDescricao(dto.descricao());
        evento.setDataInicio(dto.dataInicio());
        evento.setDataFinal(dto.dataFinal());
        evento.setLocalEvento(localEvento);
        evento.setCategoria(categoriaEvento);
        evento.setOrganizador(organizadorEvento);
        evento.setStatus(StatusEventoEnum.ATIVO.getId());
        return eventoRepository.save(evento);
    }
}
