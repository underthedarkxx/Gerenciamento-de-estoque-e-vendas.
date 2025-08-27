package com.sdias.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sdias.dto.MovimentacaoEstoqueResponseDTO;
import com.sdias.service.MovimentacaoEstoqueService;

@RestController
@RequestMapping("/api/movimentacoes-estoque")
public class MovimentacaoEstoqueController {

    @Autowired
    private MovimentacaoEstoqueService service;

    @GetMapping
    public List<MovimentacaoEstoqueResponseDTO> listarTodas() {
        return service.listarTodas();
    }
}

