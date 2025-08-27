package com.sdias.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sdias.dto.MovimentacaoEstoqueResponseDTO;
import com.sdias.repository.MovimentacaoEstoqueRepository;

@Service
public class MovimentacaoEstoqueService {

    @Autowired
    private MovimentacaoEstoqueRepository repository;

    public List<MovimentacaoEstoqueResponseDTO> listarTodas() {
        return repository.findAll()
                .stream()
                .map(MovimentacaoEstoqueResponseDTO::new)
                .collect(Collectors.toList());
    }
}

