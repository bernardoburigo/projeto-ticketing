package com.dam.backend.locaiseventos.application.usecases;

import com.dam.backend.entities.LocalEventoEntity;
import com.dam.backend.locaiseventos.infra.controllers.dto.request.LocalEventoRequestDTO;
import com.dam.backend.locaiseventos.infra.repositories.LocaisEventosRepository;
import com.dam.backend.shared.exceptions.ModelException;
import com.dam.backend.shared.utils.dto.MensagemSistema;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class CriarLocalEventoUseCase {

    private final LocaisEventosRepository locaisEventosRepository;

    @Lazy
    public CriarLocalEventoUseCase(LocaisEventosRepository locaisEventosRepository) {
        this.locaisEventosRepository = locaisEventosRepository;
    }

    public MensagemSistema criar(LocalEventoRequestDTO dto) {
        validarEntradas(dto.nome(), dto.cidade());

        buildLocalEvento(dto);

        return new MensagemSistema("Local do evento cadastrado com sucesso!");
    }

    private void validarEntradas(String nome, String cidade) {
        if (StringUtils.isBlank(nome)  || StringUtils.isBlank(cidade)) {
            String texto = Boolean.parseBoolean(nome) ? "Nome" : "Cidade";
            throw new ModelException(String.format("%s não pode ser nulo ou vazio", texto));
        }
    }

    private void buildLocalEvento(LocalEventoRequestDTO dto) {
        LocalEventoEntity localEvento = new LocalEventoEntity();
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
