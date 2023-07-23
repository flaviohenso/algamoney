package com.algaworks.algamoney_api.algamoney_api.model.chave_composta;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * Classe que representa a chave composta da enteidade Pessoa
 */
@Embeddable
public class PessoaKey implements Serializable {

    private static final long serialVersionUID = 1L;

    @GeneratedValue(strategy = GenerationType.IDENTITY) // gera o id automaticamente
    private Long id;

    private String cpf;

    public PessoaKey() {
    }

    public PessoaKey(Long id, String cpf) {
        this.id = id;
        this.cpf = cpf;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCpf() {
        return this.cpf;
    }

}
