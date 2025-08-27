package com.sdias.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

import com.sdias.model.Fornecedor;
import com.sdias.repository.FornecedorRepository;

@Service
public class FornecedorService {

    private final FornecedorRepository fornecedorRepository;

    @Autowired
    public FornecedorService(FornecedorRepository fornecedorRepository) {
        this.fornecedorRepository = fornecedorRepository;
    }

    public Fornecedor salvar(Fornecedor fornecedor) {
        if (!StringUtils.hasText(fornecedor.getNomeFantasia()) || !StringUtils.hasText(fornecedor.getCnpj())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nome Fantasia e CNPJ são obrigatórios.");
        }

        String cnpjLimpo = fornecedor.getCnpj().replaceAll("[^0-9]", "");
        fornecedor.setCnpj(cnpjLimpo);

        if (cnpjLimpo.length() != 14) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Formato de CNPJ inválido. Deve conter 14 dígitos.");
        }
        // Assumindo que o seu repository tem o método existsByCnpj
        if (fornecedor.getId() == null && fornecedorRepository.existsByCnpj(cnpjLimpo)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CNPJ já cadastrado no sistema.");
        }
        return fornecedorRepository.save(fornecedor);
    }

    public List<Fornecedor> consultarPorNome(String nome) {
        List<Fornecedor> fornecedoresEncontrados = fornecedorRepository.findByNomeFantasiaContainingIgnoreCase(nome);

        if (fornecedoresEncontrados.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum fornecedor encontrado com o nome: " + nome);
        }
        return fornecedoresEncontrados;
    }

    public List<Fornecedor> listarTodos() {
        return fornecedorRepository.findAll();
    }

    public Fornecedor buscarPorId(Long id) {
        return fornecedorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Fornecedor não encontrado."));
    }

    public void deletar(Long id) {
        fornecedorRepository.findById(id)
                .map(fornecedor -> {
                    fornecedorRepository.delete(fornecedor);
                    return Void.TYPE;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Fornecedor não encontrado"));
    }

    public Fornecedor atualizar(Long id, Fornecedor fornecedorAtualizado) {
        Fornecedor fornecedorExistente = fornecedorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Fornecedor não encontrado."));
        fornecedorExistente.setNomeFantasia(fornecedorAtualizado.getNomeFantasia());
        fornecedorExistente.setContato(fornecedorAtualizado.getContato());

        return fornecedorRepository.save(fornecedorExistente);
    }
}