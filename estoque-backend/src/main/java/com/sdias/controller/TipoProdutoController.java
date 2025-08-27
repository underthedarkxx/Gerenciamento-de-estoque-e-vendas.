package com.sdias.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sdias.model.TipoProduto;
import com.sdias.service.TipoProdutoService;

@RestController
@RequestMapping("/api/tipos-produto") // <- URL que o Spring vai reconhecer
public class TipoProdutoController {

    private final TipoProdutoService service;

    @Autowired
    public TipoProdutoController(TipoProdutoService service){
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TipoProduto salvar(@RequestBody TipoProduto tipoProduto) {
        return service.salvar(tipoProduto);
    }

    @GetMapping
    public List<TipoProduto> listarTodos() {
        return service.listarTodos();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}