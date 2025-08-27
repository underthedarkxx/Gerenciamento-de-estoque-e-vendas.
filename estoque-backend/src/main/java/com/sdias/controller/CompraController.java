package com.sdias.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sdias.dto.CompraDTO;
import com.sdias.dto.CompraResponseDTO;
import com.sdias.model.Compra;
import com.sdias.service.CompraService;

@RestController
@RequestMapping("/api/compras")
public class CompraController {

    @Autowired
    private CompraService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Compra registrarCompra(@RequestBody CompraDTO dto) {
        return service.registrarCompra(dto);
    }

    @GetMapping
    public List<CompraResponseDTO> listarTodas() {
        return service.listarTodas();
    }
}
