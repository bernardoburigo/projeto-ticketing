package com.dam.backend.locaiseventos.application.usecases;

import com.dam.backend.entities.LocalEventoEntity;
import com.dam.backend.locaiseventos.infra.controllers.dto.request.LocalEventoRequestDTO;
import com.dam.backend.locaiseventos.infra.repositories.LocaisEventosRepository;
import com.dam.backend.shared.exceptions.EntidadeNaoEncontradaException;
import com.dam.backend.shared.exceptions.ModelException;
import com.dam.backend.shared.utils.dto.MensagemSistema;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class EditarLocalEventoUseCase {

    private final LocaisEventosRepository locaisEventosRepository;

    @Lazy
    public EditarLocalEventoUseCase(LocaisEventosRepository locaisEventosRepository) {
        this.locaisEventosRepository = locaisEventosRepository;
    }

    public MensagemSistema editar(Integer id, LocalEventoRequestDTO dto) {
        validarEntradas(dto.nome(), dto.cidade());

        editarLocalEvento(id, dto);

        return new MensagemSistema("Local do evento alterado com sucesso!");
    }

    private void validarEntradas(String nome, String cidade) {
        if (StringUtils.isBlank(nome)  || StringUtils.isBlank(cidade)) {
            String texto = Boolean.parseBoolean(nome) ? "Nome" : "Cidade";
            throw new ModelException(String.format("%s não pode ser nulo ou vazio", texto));
        }
    }

    private void editarLocalEvento(Integer id, LocalEventoRequestDTO dto) {
        LocalEventoEntity localEvento = locaisEventosRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Local do evento não encontrado."));

        localEvento.setNome(dto.nome());
        localEvento.setEndereco(dto.endereco());
        localEvento.setCidade(dto.cidade());
        localEvento.setEstado(dto.estado());
        localEvento.setCep(dto.cep());
        localEvento.setCapacidade(dto.capacidade());
        localEvento.setObservacoes(dto.obs());
        locaisEventosRepository.save(localEvento);
    }
}
