package com.sdias.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sdias.model.Compra;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Long> {
}