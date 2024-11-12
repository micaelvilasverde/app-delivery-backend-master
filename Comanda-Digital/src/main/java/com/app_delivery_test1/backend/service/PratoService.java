package com.app_delivery_test1.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.app_delivery_test1.backend.model.Prato;
import com.app_delivery_test1.backend.repository.PratoRepository;

import java.util.List;
import java.util.Optional;


@Service
public class PratoService {

    private final PratoRepository pratoRepository; // Repositório para interagir com o banco de dados

    @Autowired
    public PratoService(PratoRepository pratoRepository) {
        this.pratoRepository = pratoRepository;
    }

    public Prato salvarPrato(Prato prato) {
        return pratoRepository.save(prato); // Salva o prato no banco de dados
    }

    public List<Prato> listarPratos() {
        return pratoRepository.findAll(); // Retorna todos os pratos do banco de dados
    }

    public Prato adicionarPrato(Prato prato) {
        return pratoRepository.save(prato); // Salva o prato no banco de dados
    }

    public Prato obterPratoPorNome(String nome) {
        Optional<Prato> pratoOptional = pratoRepository.findByNome(nome); // Usa o método do repositório
        return pratoOptional.orElse(null); // Retorna o prato se encontrado, ou null
    }

    public boolean deletarPrato(String nome) {
        Prato prato = obterPratoPorNome(nome);
        if (prato != null) {
            pratoRepository.delete(prato); // Deleta o prato do banco de dados
            return true;
        }
        return false;
    }
}