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

import com.sdias.model.Essencia;
import com.sdias.service.EssenciaService;

@RestController
// MELHORIA (Convenção REST): URLs de API geralmente são em minúsculas e no plural.
@RequestMapping("/api/essencias")
public class EssenciaController {

    private final EssenciaService service;

    @Autowired
    public EssenciaController(EssenciaService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Essencia salvar(@RequestBody Essencia essencia) {
        return service.salvar(essencia);
    }

    @GetMapping
    public List<Essencia> listarTodos() {
        return service.listarTodos();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }

    @PutMapping("/{id}")
    public Essencia atualizar(@PathVariable Long id, @RequestBody Essencia essencia) {
        return service.atualizar(id, essencia);
    }
}