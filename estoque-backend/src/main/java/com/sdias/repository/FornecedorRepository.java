package com.sdias.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sdias.model.Fornecedor;

@Repository
public interface FornecedorRepository extends JpaRepository<Fornecedor, Long>{
    List<Fornecedor> findByNomeFantasia(String nomeFantasia);
    boolean existsByCnpj(String cnpj);
    List<Fornecedor> findByNomeFantasiaContainingIgnoreCase(String nomeFantasia);
}