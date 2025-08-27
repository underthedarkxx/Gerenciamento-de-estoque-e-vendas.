package com.sdias.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sdias.dto.ProdutoDTO;
import com.sdias.model.Produto;
import com.sdias.service.ProdutoService;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    private final ProdutoService service;

    @Autowired
    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Produto salvar(@RequestBody ProdutoDTO dto) {
        return service.salvar(dto);
    }

    @GetMapping
    public List<Produto> listarTodos() {
        return service.listarTodos();
    }
    
    @GetMapping("/{id}")
    public Produto buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    /**
     * Endpoint para atualizar um produto existente.
     * Recebe um DTO para garantir que apenas os campos permitidos sejam alterados.
     */
    @PutMapping("/{id}")
    public Produto atualizar(@PathVariable Long id, @RequestBody ProdutoDTO dto) { // CORRIGIDO
        return service.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}