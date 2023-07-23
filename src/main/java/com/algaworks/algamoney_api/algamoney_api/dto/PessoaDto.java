package com.algaworks.algamoney_api.algamoney_api.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.algaworks.algamoney_api.algamoney_api.model.Endereco;
import com.algaworks.algamoney_api.algamoney_api.model.Pessoa;

public class PessoaDto {

    @NotBlank
    private String nome;

    @NotBlank
    private String cpf;

    @Email
    private String email;

    private String telefone;

    private Boolean ativo;

    private Endereco endereco;

    public PessoaDto() {
    }

    /*
     * Construtor para ser utilizado pelo spring boot
     */
    public PessoaDto(String nome, String cpf, String email, String telefone, Boolean ativo, Endereco endereco) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.ativo = ativo;
        this.endereco = endereco;
    }

    /*
     * Construtor para o builder
     */
    private PessoaDto(Builder builder) {
        this.nome = builder.nome;
        this.cpf = builder.cpf;
        this.email = builder.email;
        this.telefone = builder.telefone;
        this.ativo = builder.ativo;
        this.endereco = builder.endereco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    /**
     * Método para converter um objeto PessoaDto para um objeto Pessoa
     *
     * @param pessoaDto
     * @return
     */
    public Pessoa toPessoa(PessoaDto pessoaDto) {
        return new Pessoa.Builder()
                .nome(pessoaDto.getNome())
                .cpf(pessoaDto.getCpf())
                .email(pessoaDto.getEmail())
                .withTelefone(pessoaDto.getTelefone())
                .withAtivo(pessoaDto.getAtivo())
                .endereco(pessoaDto.getEndereco())
                .build();
    }

    /*
     * Builder para a classe PessoaDto
     */
    public static class Builder {

        private String nome;

        private String cpf;

        private String email;

        private String telefone;

        private Boolean ativo;

        private Endereco endereco;

        public Builder() {
        }

        public Builder nome(String nome) {
            this.nome = nome;
            return this;
        }

        public Builder cpf(String cpf) {
            this.cpf = cpf;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder withTelefone(String telefone) {
            this.telefone = telefone;
            return this;
        }

        public Builder withAtivo(Boolean ativo) {
            this.ativo = ativo;
            return this;
        }

        public Builder endereco(Endereco endereco) {
            this.endereco = endereco;
            return this;
        }

        public PessoaDto build() {
            return new PessoaDto(this);
        }

    }
}