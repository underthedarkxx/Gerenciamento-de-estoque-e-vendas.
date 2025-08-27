package com.sdias.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.sdias.dto.EmbalagemDTO;
import com.sdias.model.Embalagem;
import com.sdias.model.TipoProduto;
import com.sdias.repository.EmbalagemRepository;
import com.sdias.repository.TipoProdutoRepository;

@Service
@Transactional
public class EmbalagemService {

    private final EmbalagemRepository embalagemRepository;
    private final TipoProdutoRepository tipoProdutoRepository;

    @Autowired
    public EmbalagemService(EmbalagemRepository embalagemRepository,
                            TipoProdutoRepository tipoProdutoRepository) {
        this.embalagemRepository = embalagemRepository;
        this.tipoProdutoRepository = tipoProdutoRepository;
    }

    public Embalagem salvar(EmbalagemDTO dto) {
        TipoProduto tipoProduto = tipoProdutoRepository.findById(dto.getTipoProdutoId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "TipoProduto não encontrado"));

        Embalagem embalagem = new Embalagem();
        embalagem.setNome(dto.getNome());
        embalagem.setCusto(dto.getCusto());
        embalagem.setTipoProduto(tipoProduto);

        return embalagemRepository.save(embalagem);
    }

    public List<Embalagem> listarTodos() {
        return embalagemRepository.findAll();
    }

    public List<Embalagem> buscarPorTipo(String nomeTipo) {
        return embalagemRepository.findByTipoProdutoNome(nomeTipo);
    }

    public Embalagem buscarPorId(Long id) {
        return embalagemRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Embalagem não encontrada"));
    }

    public void deletar(Long id) {
        Embalagem embalagem = embalagemRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Embalagem não encontrada"));
        embalagemRepository.delete(embalagem);
    }

    public Embalagem atualizar(Long id, EmbalagemDTO dto) {
        Embalagem embalagem = embalagemRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Embalagem não encontrada"));

        TipoProduto tipoProduto = tipoProdutoRepository.findById(dto.getTipoProdutoId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "TipoProduto não encontrado"));

        embalagem.setNome(dto.getNome());
        embalagem.setCusto(dto.getCusto());
        embalagem.setTipoProduto(tipoProduto);

        return embalagemRepository.save(embalagem);
    }
}

