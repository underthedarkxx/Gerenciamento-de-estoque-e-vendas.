package com.sdias.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.sdias.dto.ProdutoDTO;
import com.sdias.model.Embalagem;
import com.sdias.model.Essencia;
import com.sdias.model.Produto;
import com.sdias.repository.EmbalagemRepository;
import com.sdias.repository.EssenciaRepository;
import com.sdias.repository.ProdutoRepository;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final EssenciaRepository essenciaRepository;
    private final EmbalagemRepository embalagemRepository;

    @Autowired
    public ProdutoService(
        ProdutoRepository produtoRepository,
        EssenciaRepository essenciaRepository,
        EmbalagemRepository embalagemRepository
    ) {
        this.produtoRepository = produtoRepository;
        this.essenciaRepository = essenciaRepository;
        this.embalagemRepository = embalagemRepository;
    }

    @Transactional
    public Produto salvar(ProdutoDTO dto) {
        if (produtoRepository.existsBySku(dto.getSku())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O SKU informado já está cadastrado.");
        }

        Essencia essencia = essenciaRepository.findById(dto.getEssenciaId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Essência com ID " + dto.getEssenciaId() + " não encontrada."));

        Embalagem embalagem = embalagemRepository.findById(dto.getEmbalagemId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Embalagem com ID " + dto.getEmbalagemId() + " não encontrada."));
        
        validarPreco(dto.getPrecoVenda());

        Produto produto = new Produto();
        produto.setSku(dto.getSku());
        produto.setNome(dto.getNome());
        produto.setPrecoVenda(dto.getPrecoVenda());
        produto.setEssencia(essencia);
        produto.setEmbalagem(embalagem);

        return produtoRepository.save(produto);
    }
    
    @Transactional
    public Produto atualizar(Long id, ProdutoDTO dto) {
        Produto produtoExistente = buscarPorId(id);

        validarPreco(dto.getPrecoVenda());
        produtoExistente.setPrecoVenda(dto.getPrecoVenda());

        if (dto.getNome() != null && !dto.getNome().trim().isEmpty()) {
            produtoExistente.setNome(dto.getNome());
        }

        return produtoRepository.save(produtoExistente);
    }
    
    public Produto buscarPorId(Long id) {
        return produtoRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto com ID " + id + " não encontrado."));
    }

    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    @Transactional
    public void deletar(Long id) {
        Produto produto = buscarPorId(id);
        produtoRepository.delete(produto);
    }
    
    private void validarPreco(BigDecimal preco) {
        if (preco == null || preco.compareTo(BigDecimal.ZERO) < 0) {
            // Alterado para ResponseStatusException para manter o padrão da classe
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O preço de venda não pode ser nulo ou negativo.");
        }
    }
}