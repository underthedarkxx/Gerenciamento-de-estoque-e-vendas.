package com.sdias.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sdias.model.Essencia;

@Repository
public interface EssenciaRepository extends JpaRepository<Essencia, Long>{;
}
