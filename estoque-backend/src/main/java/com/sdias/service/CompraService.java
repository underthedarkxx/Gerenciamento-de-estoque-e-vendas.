package com.sdias.service;

import com.sdias.dto.CompraDTO;
import com.sdias.dto.CompraResponseDTO;
import com.sdias.dto.ItemCompraDTO;
import com.sdias.model.*;
import com.sdias.model.enums.TipoMovimentacao;
import com.sdias.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompraService {

    @Autowired
    private CompraRepository compraRepository;
    @Autowired
    private FornecedorRepository fornecedorRepository;
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private MovimentacaoEstoqueRepository movimentacaoEstoqueRepository;
    @Autowired
    private ItemCompraRepository itemCompraRepository;

    @Transactional
    public Compra registrarCompra(CompraDTO dto) {
        Fornecedor fornecedor = fornecedorRepository.findById(dto.fornecedorId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Fornecedor não encontrado."));

        Compra novaCompra = new Compra();
        novaCompra.setFornecedor(fornecedor);
        novaCompra.setDataCompra(LocalDateTime.now());
        
        List<ItemCompra> itensDaCompra = new ArrayList<>();
        BigDecimal valorTotal = BigDecimal.ZERO;
        
        for (ItemCompraDTO itemDTO : dto.itens()) {
            Produto produto = produtoRepository.findById(itemDTO.produtoId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto com ID " + itemDTO.produtoId() + " não encontrado."));

            produto.setQuantidade(produto.getQuantidade() + itemDTO.quantidade());
            produtoRepository.save(produto);

            MovimentacaoEstoque movimentacao = new MovimentacaoEstoque();
            movimentacao.setProduto(produto);
            movimentacao.setTipoMovimentacao(TipoMovimentacao.ENTRADA);
            movimentacao.setQuantidade(itemDTO.quantidade());
            movimentacao.setCompra(novaCompra);
            movimentacaoEstoqueRepository.save(movimentacao);

            ItemCompra item = new ItemCompra();
            item.setCompra(novaCompra);
            item.setProduto(produto);
            item.setQuantidade(itemDTO.quantidade());
            item.setCustoUnitario(itemDTO.custoUnitario());
            itensDaCompra.add(item);
            
            valorTotal = valorTotal.add(item.getCustoUnitario().multiply(BigDecimal.valueOf(item.getQuantidade())));
        }

        novaCompra.setItens(itensDaCompra);
        novaCompra.setValorTotal(valorTotal);
        
        return compraRepository.save(novaCompra);
    }

    @Transactional
    public List<CompraResponseDTO> listarTodas() {
        List<Compra> compras = compraRepository.findAll();
        // A anotação @Transactional garante que as coleções LAZY sejam carregadas
        return compras.stream()
                .map(CompraResponseDTO::new)
                .collect(Collectors.toList());
    }
}

