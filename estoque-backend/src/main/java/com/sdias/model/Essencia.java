package com.sdias.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "essencias")
public class Essencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String nome; // Ex: "Lavanda do Campo", "Bambu Imperial", "Verbena"

    @Column(name = "descricao_olfativa", length = 500)
    private String descricaoOlfativa;

    // Construtor vazio (obrigatório pelo JPA)
    public Essencia() {
    }

    // Construtor para facilitar a criação
    public Essencia(String nome, String descricaoOlfativa) {
        this.nome = nome;
        this.descricaoOlfativa = descricaoOlfativa;
    }

    // --- Getters e Setters ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricaoOlfativa() {
        return descricaoOlfativa;
    }

    public void setDescricaoOlfativa(String descricaoOlfativa) {
        this.descricaoOlfativa = descricaoOlfativa;
    }
}