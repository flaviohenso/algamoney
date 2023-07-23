package com.algaworks.algamoney_api.algamoney_api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    private String nome;

    private String descricao;

    public Categoria() {
    }

    /**
     * @param codigo    Long
     * @param nome      String
     * @param descricao String
     */
    public Categoria(Long codigo, String nome, String descricao) {
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
    }

    /*
     * construtor para o builder
     */
    private Categoria(Builder builder) {
        this.codigo = builder.codigo;
        this.nome = builder.nome;
        this.descricao = builder.descricao;
    }

    /**
     * @return codigo Long
     */
    public Long getCodigo() {
        return codigo;
    }

    /**
     * @param codigo Long
     */
    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    /**
     * @return nome String
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome String
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return descricao String
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao String
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /*
     * Builder para criar uma categoria
     */
    public static class Builder {
        private Long codigo;
        private String nome;
        private String descricao;

        public Builder() {
        }

        /**
         * @param codigo
         * @return Builder
         */
        public Builder codigo(Long codigo) {
            this.codigo = codigo;
            return this;
        }

        /**
         * @param nome
         * @return Builder
         */
        public Builder nome(String nome) {
            this.nome = nome;
            return this;
        }

        /**
         * @param descricao
         * @return Builder
         */
        public Builder descricao(String descricao) {
            this.descricao = descricao;
            return this;
        }

        /**
         * @return Categoria
         */
        public Categoria build() {
            return new Categoria(this);
        }
    }
}
