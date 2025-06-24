package com.dam.backend.locaiseventos.application.usecases;

import com.dam.backend.entities.LocalEventoEntity;
import com.dam.backend.locaiseventos.infra.repositories.LocaisEventosRepository;
import com.dam.backend.shared.exceptions.EntidadeNaoEncontradaException;
import com.dam.backend.shared.utils.dto.MensagemSistema;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class DeletarLocalEventoUseCase {

    private final LocaisEventosRepository locaisEventosRepository;

    public DeletarLocalEventoUseCase(LocaisEventosRepository locaisEventosRepository) {
        this.locaisEventosRepository = locaisEventosRepository;
    }

    @Lazy
    public MensagemSistema deletar(Integer id) {
        LocalEventoEntity localEvento = locaisEventosRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Local do evento não encontrado."));

        localEvento.setAtivo(false);
        localEvento.setExcluido(true);

        locaisEventosRepository.save(localEvento);

        return new MensagemSistema("Local do evento excluído com sucesso!");
    }
}
