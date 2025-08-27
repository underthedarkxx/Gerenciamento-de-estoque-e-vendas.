package com.sdias.service;

import com.sdias.dto.CompraDTO;
import com.sdias.dto.CompraResponseDTO; // Importe o DTO de resposta
import com.sdias.dto.ItemCompraDTO;
import com.sdias.model.*;
import com.sdias.model.enums.TipoMovimentacao;
import com.sdias.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors; // Import necessário para a conversão

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
        
        Compra compraSalva = compraRepository.save(novaCompra);

        List<ItemCompra> itensDaCompra = new ArrayList<>();
        
        for (ItemCompraDTO itemDTO : dto.itens()) {
            Produto produto = produtoRepository.findById(itemDTO.produtoId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto com ID " + itemDTO.produtoId() + " não encontrado."));

            produto.setQuantidade(produto.getQuantidade() + itemDTO.quantidade());
            produtoRepository.save(produto);

            MovimentacaoEstoque movimentacao = new MovimentacaoEstoque();
            movimentacao.setProduto(produto);
            movimentacao.setTipoMovimentacao(TipoMovimentacao.ENTRADA);
            movimentacao.setQuantidade(itemDTO.quantidade());
            movimentacao.setCompra(compraSalva);
            movimentacaoEstoqueRepository.save(movimentacao);

            ItemCompra item = new ItemCompra();
            item.setCompra(compraSalva);
            item.setProduto(produto);
            item.setQuantidade(itemDTO.quantidade());
            item.setCustoUnitario(itemDTO.custoUnitario());
            itensDaCompra.add(item);
        }

        itemCompraRepository.saveAll(itensDaCompra);
        compraSalva.setItens(itensDaCompra);

        return compraSalva;
    }

    // =======================================================
    // MÉTODO CORRIGIDO PARA DEVOLVER O TIPO CERTO
    // =======================================================
    public List<CompraResponseDTO> listarTodas() {
        return compraRepository.findAll() // 1. Pega todas as entidades Compra
                .stream()                 // 2. Transforma a lista num fluxo de dados
                .map(CompraResponseDTO::new) // 3. Para cada Compra, cria um novo CompraResponseDTO
                .collect(Collectors.toList()); // 4. Junta tudo numa nova lista de DTOs
    }
}


