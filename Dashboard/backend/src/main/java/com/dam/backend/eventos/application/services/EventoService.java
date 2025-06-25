package com.dam.backend.eventos.application.services;

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
import com.dam.backend.shared.utils.PaginationUtil;
import com.dam.backend.shared.utils.dto.BooleanRequestDTO;
import com.dam.backend.shared.utils.dto.MensagemSistema;
import com.dam.backend.shared.utils.dto.PaginarDTO;
import java.util.Optional;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class EventoService {

    private final EventoRepository eventoRepository;
    private final LocaisEventosRepositoryGateway locaisEventosRepositoryGateway;
    private final UsuarioRepositoryGateway usuarioRepositoryGateway;
    private final CategoriaEventoRepository categoriaEventoRepository;
    private final ImagemService imagemService;

    @Lazy
    public EventoService(EventoRepository eventoRepository,
                         LocaisEventosRepositoryGateway locaisEventosRepositoryGateway,
                         UsuarioRepositoryGateway usuarioRepositoryGateway,
                         CategoriaEventoRepository categoriaEventoRepository,
                         ImagemService imagemService) {
        this.eventoRepository = eventoRepository;
        this.locaisEventosRepositoryGateway = locaisEventosRepositoryGateway;
        this.usuarioRepositoryGateway = usuarioRepositoryGateway;
        this.categoriaEventoRepository = categoriaEventoRepository;
        this.imagemService = imagemService;
    }

    public EventoResponseDTO criar(EventoRequestDTO dto, MultipartFile file) {
        validarResposta(dto);

        LocalEventoEntity localEvento = buscarLocaisEventos(dto.localEvento());
        CategoriaEventoEntity categoriaEvento = buscarCategoriaEvento(dto.categoria());
        UsuarioEntity usuarioEvento = buscarUsuarioEvento(dto.organizador());
        EventosEntity evento = buildEvento(dto, localEvento, categoriaEvento, usuarioEvento, file);
        Integer statusEvento = EventoMapper.statusEvento(evento);

        evento.setStatus(statusEvento);
        eventoRepository.save(evento);

        return EventoMapper.toDTO(evento, localEvento, categoriaEvento, usuarioEvento);
    }

    public EventoResponseDTO editar(Integer id, EventoRequestDTO dto, MultipartFile file) {

        EventosEntity evento = buscarEvento(id);
        LocalEventoEntity localEvento = buscarLocaisEventos(dto.localEvento());
        CategoriaEventoEntity categoriaEvento = buscarCategoriaEvento(dto.categoria());
        UsuarioEntity organizadorEvento = buscarUsuarioEvento(dto.organizador());
        Integer statusEvento = EventoMapper.statusEvento(evento);

        String imagemNome = imagemService.editarImagem(evento.getImagemNome(), file);

        evento.setNome(dto.nome());
        evento.setDescricao(dto.descricao());
        evento.setDataInicio(dto.dataInicio());
        evento.setDataFinal(dto.dataFinal());
        evento.setLocalEvento(localEvento);
        evento.setCategoria(categoriaEvento);
        evento.setOrganizador(organizadorEvento);
        evento.setStatus(statusEvento);
        evento.setImagemNome(imagemNome);
        eventoRepository.save(evento);

        return EventoMapper.toDTO(evento, localEvento, categoriaEvento, organizadorEvento);
    }

    public EventoResponseDTO buscar(Integer id) {
        EventosEntity evento = buscarEvento(id);
        LocalEventoEntity localEvento = buscarLocaisEventos(evento.getLocalEvento().getId());
        CategoriaEventoEntity categoria = buscarCategoriaEvento(evento.getCategoria().getId());
        UsuarioEntity organizador = buscarUsuarioEvento(evento.getOrganizador().getId());

        return EventoMapper.toDTO(evento, localEvento, categoria, organizador);
    }

    public Page<EventoResponseDTO> paginar(PaginarDTO dto) {
        PageRequest pageRequest = PaginationUtil.paginar(dto);

        Page<EventosEntity> eventos = eventoRepository.paginarPorAtivo(dto.search(), pageRequest);

        if (eventos.isEmpty()) {
            return PaginationUtil.paginaVazia(pageRequest);
        }

        return eventos.map(evento -> {
            LocalEventoEntity localEvento = buscarLocaisEventos(evento.getLocalEvento().getId());
            CategoriaEventoEntity categoria = buscarCategoriaEvento(evento.getCategoria().getId());
            UsuarioEntity organizador = buscarUsuarioEvento(evento.getOrganizador().getId());

            return EventoMapper.toDTO(evento, localEvento, categoria, organizador);
        });
    }

    public MensagemSistema deletar(Integer id) {
        EventosEntity evento = buscarEvento(id);
        imagemService.deletarImagem(evento.getImagemNome());

        evento.setAtivo(false);
        evento.setExcluido(true);
        evento.setStatus(StatusEventoEnum.EXCLUIDO.getId());
        eventoRepository.save(evento);

        return new MensagemSistema("Evento removido com sucesso!");
    }

    public MensagemSistema cancelarEvento(Integer id, BooleanRequestDTO dto) {
        EventosEntity evento = buscarEvento(id);

        ConstraintsUtil.requireNonNull(evento.isAtivo() || dto.value(), "Status não pode estar nulo para inativar");

        evento.setAtivo(dto.value());
        evento.setStatus(StatusEventoEnum.CANCELADO.getId());
        eventoRepository.save(evento);

        return new MensagemSistema("Evento cancelado com sucesso!");
    }

    private void validarResposta(EventoRequestDTO dto) {
        if (org.apache.commons.lang3.StringUtils.isBlank(dto.nome())) {
            throw new ModelException("Nome não pode ser nulo ou vazio");
        }

        ConstraintsUtil.requireNonNull(dto.dataInicio(), "Data do inicio do evento não poder ser nula");
        ConstraintsUtil.requireNonNull(dto.dataFinal(), "Data do final do evento não poder ser nula");
        ConstraintsUtil.requireNonNull(dto.localEvento(), "Local do evento não pode ser nulo");
        ConstraintsUtil.requireNonNull(dto.categoria(), "Categoria do evento não poder ser nula");
        ConstraintsUtil.requireNonNull(dto.organizador(), "Organizador do evento não poder ser nulo");
    }

    private EventosEntity buscarEvento(Integer id) {
        return Optional.ofNullable(eventoRepository.findByIdAndAtivo(id))
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Evento não encontrado."));
    }

    private LocalEventoEntity buscarLocaisEventos(Integer id) {
        return locaisEventosRepositoryGateway.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Local de evento não encontrado."));
    }

    private CategoriaEventoEntity buscarCategoriaEvento(Integer id) {
        return categoriaEventoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Categoria do evento não encontrado."));
    }

    private UsuarioEntity buscarUsuarioEvento(Integer id) {
        UsuarioEntity usuario = usuarioRepositoryGateway.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Usuário do evento não encontrado."));

        validarOrganizador(usuario);
        return usuario;
    }

    private void validarOrganizador(UsuarioEntity usuario) {
        if (!usuario.isAtivo() || usuario.isExcluido()) {
            throw new ModelException("Não pode criar evento com organizador inativo ou excluído");
        }
    }

    private EventosEntity buildEvento(
            EventoRequestDTO dto,
            LocalEventoEntity localEvento,
            CategoriaEventoEntity categoriaEvento,
            UsuarioEntity organizadorEvento,
            MultipartFile file) {
        String nameImage = null;

        if (file != null && !file.isEmpty()) {
            nameImage = imagemService.salvarImagem(file);
        }

        EventosEntity evento = new EventosEntity();
        evento.setNome(dto.nome());
        evento.setDescricao(dto.descricao());
        evento.setDataInicio(dto.dataInicio());
        evento.setDataFinal(dto.dataFinal());
        evento.setLocalEvento(localEvento);
        evento.setCategoria(categoriaEvento);
        evento.setOrganizador(organizadorEvento);
        evento.setImagemNome(nameImage);

        return evento;
    }
}
