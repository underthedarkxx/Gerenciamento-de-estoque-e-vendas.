package com.sdias.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sdias.model.MovimentacaoEstoque;

@Repository
public interface MovimentacaoEstoqueRepository extends JpaRepository<MovimentacaoEstoque, Long> {

    List<MovimentacaoEstoque> findByProdutoId(Long produtoId);

    List<MovimentacaoEstoque> findByTipoMovimentacao(com.sdias.model.enums.TipoMovimentacao tipo);

    List<MovimentacaoEstoque> findByCompraId(Long compraId);

    List<MovimentacaoEstoque> findByPedidoId(Long pedidoId);
}


