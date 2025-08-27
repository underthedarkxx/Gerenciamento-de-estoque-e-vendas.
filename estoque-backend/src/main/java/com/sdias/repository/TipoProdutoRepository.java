package com.sdias.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sdias.model.TipoProduto;

@Repository
public interface TipoProdutoRepository extends JpaRepository<TipoProduto, Long> {
    boolean existsByNome(String nome);
}