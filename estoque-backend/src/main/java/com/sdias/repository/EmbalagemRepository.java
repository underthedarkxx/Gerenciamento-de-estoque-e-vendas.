package com.sdias.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sdias.model.Embalagem;

@Repository
public interface EmbalagemRepository extends JpaRepository<Embalagem, Long>{
    List<Embalagem> findByTipoProdutoNome(String nomeTipo);
}