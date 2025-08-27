package com.sdias.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.sdias.model.Essencia;
import com.sdias.repository.EssenciaRepository;

@Service
public class EssenciaService {

    private final EssenciaRepository repository;

    @Autowired
    public EssenciaService(EssenciaRepository repository) {
        this.repository = repository;
    }

    public Essencia salvar(Essencia essencia) {
        return repository.save(essencia);
    }

    public List<Essencia> listarTodos() {
        return repository.findAll();
    }

    // MÉTODO ATUALIZAR (ADICIONADO E INTEGRADO)
    public Essencia atualizar(Long id, Essencia essenciaAtualizada) {
        // Busca a essência antiga no banco ou lança um erro 404 se não existir
        Essencia essenciaAntiga = repository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Essência não encontrada"));

        // Atualiza os dados do objeto existente com os novos dados
        essenciaAntiga.setNome(essenciaAtualizada.getNome());
        essenciaAntiga.setDescricaoOlfativa(essenciaAtualizada.getDescricaoOlfativa());

        // Salva o objeto atualizado (o JPA entende que é um UPDATE)
        return repository.save(essenciaAntiga);
    }

    // MÉTODO DELETAR (REFINADO)
    public void deletar(Long id) {
        // Esta verificação garante que um erro 404 seja retornado se o ID não existir.
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Essência não encontrada.");
        }
        // Este método é otimizado para apenas deletar, sem precisar buscar o objeto inteiro antes.
        repository.deleteById(id);
    }
}