package com.sdias.repository;

import java.util.List; // Importe o novo DTO

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sdias.dto.HistoricoCompraClienteDTO;
import com.sdias.model.ItemPedido;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {
    
    List<ItemPedido> findByPedidoId(Long pedidoId);

    // Adicione este novo método
    @Query("SELECT new com.sdias.dto.HistoricoCompraClienteDTO(" +
        "ip.produto.essencia.nome, " +
        "ip.produto.embalagem.tipoProduto.nome, " +
        "ip.quantidade, " +
        "ip.precoUnitario, " +
        "ip.pedido.dataPedido) " +
        "FROM ItemPedido ip " +
        "WHERE ip.pedido.cliente.id = :clienteId " +
        "ORDER BY ip.pedido.dataPedido DESC")
    List<HistoricoCompraClienteDTO> findHistoricoComprasPorCliente(@Param("clienteId") Long clienteId);
}