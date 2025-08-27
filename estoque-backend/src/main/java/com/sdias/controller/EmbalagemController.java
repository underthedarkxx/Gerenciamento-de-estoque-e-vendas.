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

import com.sdias.dto.EmbalagemDTO;
import com.sdias.model.Embalagem;
import com.sdias.service.EmbalagemService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/embalagens")
public class EmbalagemController {

    private final EmbalagemService service;

    @Autowired
    public EmbalagemController(EmbalagemService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Embalagem salvar(@RequestBody @Valid EmbalagemDTO dto) {
        return service.salvar(dto);
    }

    @GetMapping
    public List<Embalagem> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/buscar")
    public List<Embalagem> buscarPorTipo(@RequestParam String nomeTipo) {
        return service.buscarPorTipo(nomeTipo);
    }

    @GetMapping("/{id}")
    public Embalagem buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }

    @PutMapping("/{id}")
    public Embalagem atualizar(@PathVariable Long id, @RequestBody @Valid EmbalagemDTO dto) {
        return service.atualizar(id, dto);
    }
}
