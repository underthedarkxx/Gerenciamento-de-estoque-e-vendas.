package com.sdias.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.sdias.dto.ItemPedidoDTO; // <- Adicionar import
import com.sdias.dto.PedidoCreateDTO;
import com.sdias.model.Cliente;
import com.sdias.model.ItemPedido;
import com.sdias.model.Pedido; // <- Adicionar import
import com.sdias.model.Produto;
import com.sdias.model.enums.StatusPedido; // <- Adicionar import
import com.sdias.repository.ClienteRepository;
import com.sdias.repository.ItemPedidoRepository;
import com.sdias.repository.PedidoRepository; // <- Adicionar import
import com.sdias.repository.ProdutoRepository;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository; // <- Adicionar dependência
    private final ItemPedidoRepository itemPedidoRepository; // <- Adicionar dependência

    @Autowired
    public PedidoService(PedidoRepository pedidoRepository, ClienteRepository clienteRepository, ProdutoRepository produtoRepository, ItemPedidoRepository itemPedidoRepository){ // <- Atualizar construtor
        this.pedidoRepository = pedidoRepository;
        this.clienteRepository = clienteRepository;
        this.produtoRepository = produtoRepository;
        this.itemPedidoRepository = itemPedidoRepository;
    }

    @Transactional
    public Pedido criar(PedidoCreateDTO dto){
        Cliente cliente = clienteRepository.findById(dto.getClienteId())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente " + dto.getClienteId() + " não encontrado."));

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setStatus(StatusPedido.PENDENTE);
        pedido.setValorTotal(BigDecimal.ZERO);

        Pedido pedidoSalvo = pedidoRepository.save(pedido);

        // Adicionar itens, se houver
        if(dto.getItens() != null){
            for(ItemPedidoDTO itemDTO : dto.getItens()){
                adicionarItem(pedidoSalvo.getId(), itemDTO);
            }
        }

        return pedidoSalvo;
    }




    public List<Pedido> listarTodos(){
        return pedidoRepository.findAll();
    }
    
    public Pedido buscarPorId(Long id){
        return pedidoRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido com ID " + id + " não encontrado."));
        }


    @Transactional
    public Pedido adicionarItem(Long pedidoId, ItemPedidoDTO itemDTO) {
        Pedido pedido = buscarPorId(pedidoId);
        Produto produto = produtoRepository.findById(itemDTO.getProdutoId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto com ID " + itemDTO.getProdutoId() + " não encontrado."));

        // Criar e salvar o novo item
        ItemPedido novoItem = new ItemPedido();
        novoItem.setPedido(pedido);
        novoItem.setProduto(produto);
        novoItem.setQuantidade(itemDTO.getQuantidade());
        novoItem.setPrecoUnitario(produto.getPrecoVenda()); // "Congela" o preço no momento da compra
        itemPedidoRepository.save(novoItem);


        BigDecimal valorItem = produto.getPrecoVenda().multiply(new BigDecimal(itemDTO.getQuantidade()));
        pedido.setValorTotal(pedido.getValorTotal().add(valorItem));
        
        return pedidoRepository.save(pedido);
    }


    @Transactional
    public Pedido atualizarStatus(Long id, StatusPedido novoStatus) {
        Pedido pedido = buscarPorId(id);
        pedido.setStatus(novoStatus);
        return pedidoRepository.save(pedido);
    }
    
    @Transactional
    public void deletar(Long id) {
        if (!pedidoRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido com ID " + id + " não encontrado.");
        }
        pedidoRepository.deleteById(id);
    }
}
