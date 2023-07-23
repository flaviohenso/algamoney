package com.algaworks.algamoney_api.algamoney_api.dto;

import javax.validation.constraints.Email;

public class PessoaUpdateDto {

    private String email;
    private String telefone;
    private Boolean ativo;

    public PessoaUpdateDto() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

}
