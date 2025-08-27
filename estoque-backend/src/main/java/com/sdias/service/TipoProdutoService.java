package com.sdias.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.sdias.model.TipoProduto;
import com.sdias.repository.TipoProdutoRepository;

@Service
public class TipoProdutoService {

    @Autowired
    private TipoProdutoRepository repository;

    public TipoProduto salvar(TipoProduto tipoProduto) {

        boolean nomeJaExiste = repository.existsByNome(tipoProduto.getNome());


        if (nomeJaExiste) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Um tipo de produto com este nome já foi cadastrado.");
        }

        return repository.save(tipoProduto);
    }

    public List<TipoProduto> listarTodos() {
        return repository.findAll();
    }

    public void deletar(Long id) {
        repository.findById(id)
                .map(tipo -> {
                    repository.delete(tipo);
                    return Void.TYPE;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tipo de Produto não encontrado."));
    }
}