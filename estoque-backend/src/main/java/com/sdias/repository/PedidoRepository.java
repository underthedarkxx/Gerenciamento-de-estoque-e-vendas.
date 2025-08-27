package com.sdias.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sdias.dto.HistoricoCompraClienteDTO;
import com.sdias.model.Cliente;
import com.sdias.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    List<Pedido> findByCliente(Cliente cliente);

    @Query("SELECT new com.sdias.dto.HistoricoCompraClienteDTO(" +
        "e.nome, pr.sku, i.quantidade, i.precoUnitario, p.dataPedido) " +
        "FROM Pedido p " +
        "JOIN p.itens i " +
        "JOIN i.produto pr " +
        "JOIN pr.essencia e " +
        "WHERE p.cliente.id = :clienteId " +
        "ORDER BY p.dataPedido DESC")
    List<HistoricoCompraClienteDTO> findHistoricoByClienteId(@Param("clienteId") Long clienteId);

}