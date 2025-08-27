package com.sdias.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.sdias.dto.ItemPedidoDTO;
import com.sdias.model.ItemPedido;
import com.sdias.model.Pedido;
import com.sdias.model.Produto;
import com.sdias.model.enums.StatusPedido;
import com.sdias.repository.ItemPedidoRepository;
import com.sdias.repository.PedidoRepository;
import com.sdias.repository.ProdutoRepository;

@Service
public class ItemPedidoService{

    private final ItemPedidoRepository itemPedidoRepository;
    private final PedidoRepository pedidoRepository;
    private final ProdutoRepository produtoRepository;

    @Autowired
    public ItemPedidoService(ItemPedidoRepository itemPedidoRepository, PedidoRepository pedidoRepository, ProdutoRepository produtoRepository){
        this.itemPedidoRepository = itemPedidoRepository;
        this.pedidoRepository = pedidoRepository;
        this.produtoRepository = produtoRepository;
    }

    @Transactional
    public Pedido adicionarItem(Long pedidoId, ItemPedidoDTO itemDTO){
        Pedido pedido = pedidoRepository.findById(pedidoId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado"));

        Produto produto = produtoRepository.findById(itemDTO.getProdutoId())
            .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado."));

        if(pedido.getStatus() != StatusPedido.PENDENTE){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Apenas pedidos com status PENDENTE podem ser modificados.");
        }

        ItemPedido novoItem = new ItemPedido();
        novoItem.setPedido(pedido);
        novoItem.setProduto(produto);
        novoItem.setQuantidade(itemDTO.getQuantidade());

        novoItem.setPrecoUnitario(produto.getPrecoVenda());

        itemPedidoRepository.save(novoItem);

        recalcularEAtualizarValorTotal(pedido);

        return pedido;
    }

    @Transactional
    public Pedido removerItem(Long pedidoId, Long itemId) {
        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado."));
        
        if (pedido.getStatus() != StatusPedido.PENDENTE) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Apenas pedidos com status PENDENTE podem ser modificados.");
        }

        ItemPedido itemParaRemover = itemPedidoRepository.findById(itemId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item de pedido não encontrado."));
        
        if (!itemParaRemover.getPedido().getId().equals(pedidoId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O item não pertence ao pedido informado.");
        }

        itemPedidoRepository.delete(itemParaRemover);
        
        recalcularEAtualizarValorTotal(pedido);
        return pedido;
    }
    
    private void recalcularEAtualizarValorTotal(Pedido pedido){
        List<ItemPedido> itens = itemPedidoRepository.findByPedidoId(pedido.getId());

        BigDecimal valorTotal = itens.stream()
            .map(item -> item.getPrecoUnitario().multiply(new BigDecimal(item.getQuantidade())))
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        pedido.setValorTotal(valorTotal);
        pedidoRepository.save(pedido);
    }
}