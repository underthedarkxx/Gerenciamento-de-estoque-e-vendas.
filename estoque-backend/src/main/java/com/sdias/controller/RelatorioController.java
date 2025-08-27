package com.sdias.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sdias.dto.RelatorioVendasClienteDTO;
import com.sdias.service.RelatorioService;

@RestController
@RequestMapping("/api/relatorios")
public class RelatorioController {

    @Autowired
    private RelatorioService relatorioService;

    @GetMapping("/estoque-baixo")
    public java.util.List<com.sdias.model.Produto> getEstoqueBaixo(@RequestParam(value = "limite", defaultValue = "10") Integer limite) {
        return relatorioService.findEstoqueBaixo(limite);
    }

    @GetMapping("/vendas-cliente/{clienteId}")
    public RelatorioVendasClienteDTO getVendasPorCliente(@PathVariable Long clienteId) { // O DTO também é reconhecido
        return relatorioService.findVendasByCliente(clienteId);
    }
}