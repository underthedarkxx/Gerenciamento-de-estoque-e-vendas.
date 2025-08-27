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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sdias.model.Fornecedor;
import com.sdias.service.FornecedorService;

@RestController
@RequestMapping("/api/fornecedores")
public class FornecedorController {

    private final FornecedorService service;

    @Autowired
    public FornecedorController(FornecedorService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Fornecedor adicionar(@RequestBody Fornecedor novoFornecedor) {
        return service.salvar(novoFornecedor);
    }

    @GetMapping
    public List<Fornecedor> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public Fornecedor buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @GetMapping("/buscar")
    public List<Fornecedor> buscaPorNome(@RequestParam String nome) {
        return service.consultarPorNome(nome);
    }

    @PutMapping("/{id}")
    public Fornecedor atualizar(@PathVariable Long id, @RequestBody Fornecedor fornecedor) {
        return service.atualizar(id, fornecedor);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}