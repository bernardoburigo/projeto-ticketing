package com.dam.backend.eventos.application.services;

import com.dam.backend.shared.exceptions.ModelException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImagemService {

    private final Path caminhoImagens;

    public ImagemService(@Value("${caminho.imagens}") String caminho) {
        this.caminhoImagens = Paths.get(caminho).toAbsolutePath().normalize();

        try {
            Files.createDirectories(caminhoImagens);
        } catch (IOException e) {
            throw new ModelException(e.getMessage());
        }
    }

    public String salvarImagem(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return null;
            }

            String nomeArquivo = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path destino = caminhoImagens.resolve(nomeArquivo);
            Files.write(destino, file.getBytes());

            return nomeArquivo;
        } catch (IOException e) {
            throw new ModelException("Erro ao completar download", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public void deletarImagem(String nome) {
        if (StringUtils.isBlank(nome)) {
            return;
        }

        try {
            Path caminhoArquivo = caminhoImagens.resolve(nome).normalize();

            if (Files.exists(caminhoArquivo)) {
                Files.delete(caminhoArquivo);
            }
        } catch (IOException e) {
            throw new ModelException("Erro ao deletar imagem", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public String editarImagem(String nomeAntigo, MultipartFile novaImagem) {
        try {
            if (StringUtils.isNotBlank(nomeAntigo)) {
                Path caminhoAntigo = caminhoImagens.resolve(nomeAntigo).normalize();
                if (Files.exists(caminhoAntigo)) {
                    Files.delete(caminhoAntigo);
                }
            }

            if (novaImagem != null && !novaImagem.isEmpty()) {
                String nomeNovo = UUID.randomUUID() + "_" + novaImagem.getOriginalFilename();
                Path destino = caminhoImagens.resolve(nomeNovo).normalize();
                Files.write(destino, novaImagem.getBytes());
                return nomeNovo;
            }

            return null;
        } catch (IOException e) {
            throw new ModelException("Erro ao editar imagem", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
