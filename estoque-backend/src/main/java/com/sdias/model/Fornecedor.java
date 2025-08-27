/*
CREATE TABLE IF NOT EXISTS fornecedores (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome_fantasia VARCHAR(150) NOT NULL,
    cnpj VARCHAR(20) UNIQUE,
    contato VARCHAR(100)
);
 */

package com.sdias.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name = "fornecedores")
public class Fornecedor{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="nome_fantasia", nullable = false, length = 150)
    private String nomeFantasia;

    @Column(unique = true, length= 20)
    private String cnpj;

    @Column(length= 100)
    private String contato;

    public Fornecedor(){
    }

    public Fornecedor(String nomeFantasia, String cnpj, String contato) {
        this.nomeFantasia = nomeFantasia;
        this.cnpj = cnpj;
        this.contato = contato;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getNomeFantasia(){
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia){
        this.nomeFantasia = nomeFantasia;
    }

    public String getCnpj(){
        return cnpj;
    }

    public void setCnpj(String cnpj){
        this.cnpj = cnpj;
    }

    public String getContato(){
        return contato;
    }

    public void setContato(String contato){
        this.contato = contato;
    }
}