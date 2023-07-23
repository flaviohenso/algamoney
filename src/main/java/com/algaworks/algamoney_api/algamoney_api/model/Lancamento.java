package com.algaworks.algamoney_api.algamoney_api.model;

import static javax.persistence.EnumType.STRING;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "lancamento")
public class Lancamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long codigo;

    private String descricao;

    @Column(name = "data_vencimento")
    private LocalDate dataVencimento;

    @Column(name = "data_pagamento")
    private LocalDate dataPagamento;

    private BigDecimal valor;
    private String observacao;

    @Enumerated(STRING)
    private TipoLancamento tipo;

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "id_pessoa")
    private Pessoa pessoa;

    public Lancamento() {
    }

    /**
     * Construtor para o builder
     */
    private Lancamento(Builder builder) {
        this.descricao = builder.descricao;
        this.dataVencimento = builder.dataVencimento;
        this.dataPagamento = builder.dataPagamento;
        this.valor = builder.valor;
        this.observacao = builder.observacao;
        this.tipo = builder.tipo;
        this.categoria = builder.categoria;
        this.pessoa = builder.pessoa;
        this.codigo = builder.codigo;
    }

    /**
     * @return the codigo
     */
    public Long getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return the dataVencimento
     */
    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    /**
     * @param dataVencimento the dataVencimento to set
     */
    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    /**
     * @return the dataPagamento
     */
    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    /**
     * @param dataPagamento the dataPagamento to set
     */
    public void setDataPagamento(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    /**
     * @return the valor
     */
    public BigDecimal getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    /**
     * @return the observacao
     */
    public String getObservacao() {
        return observacao;
    }

    /**
     * @param observacao the observacao to set
     */
    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    /**
     * @return the tipo
     */
    public TipoLancamento getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(TipoLancamento tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the categoria
     */
    public Categoria getCategoria() {
        return categoria;
    }

    /**
     * @param categoria the categoria to set
     */
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    /**
     * @return the pessoa
     */
    public Pessoa getPessoa() {
        return pessoa;
    }

    /**
     * @param pessoa the pessoa to set
     */
    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    /* equals e Hash */

    /**
     * Builder para a classe Lancamento
     */
    public static class Builder {

        private String descricao;
        private LocalDate dataVencimento;
        private LocalDate dataPagamento;
        private BigDecimal valor;
        private String observacao;
        private TipoLancamento tipo;
        private Categoria categoria;
        private Pessoa pessoa;
        private Long codigo;

        /**
         * @param codigo Long
         * @return this
         */
        public Builder codigo(Long codigo) {
            this.codigo = codigo;
            return this;
        }

        /**
         * @param descricao String
         * @return this
         */
        public Builder descricao(String descricao) {
            this.descricao = descricao;
            return this;
        }

        /**
         * @param dataVencimento LocalDate
         * @return this
         */
        public Builder dataVencimento(LocalDate dataVencimento) {
            this.dataVencimento = dataVencimento;
            return this;
        }

        /**
         * @param dataPagamento LocalDate
         * @return this
         */
        public Builder dataPagamento(LocalDate dataPagamento) {
            this.dataPagamento = dataPagamento;
            return this;
        }

        /**
         * @param valor BigDecimal
         * @return this
         */
        public Builder valor(BigDecimal valor) {
            this.valor = valor;
            return this;
        }

        /**
         * @param observacao String
         * @return this
         */
        public Builder observacao(String observacao) {
            this.observacao = observacao;
            return this;
        }

        /**
         * @param tipo TipoLancamento
         * @return this
         */
        public Builder tipo(TipoLancamento tipo) {
            this.tipo = tipo;
            return this;
        }

        /**
         * @param categoria Categoria
         * @return this
         */
        public Builder categoria(Categoria categoria) {
            this.categoria = categoria;
            return this;
        }

        /**
         * @param pessoa Pessoa
         * @return this
         */
        public Builder pessoa(Pessoa pessoa) {
            this.pessoa = pessoa;
            return this;
        }

        /**
         * @return Lancamento
         */
        public Lancamento build() {
            return new Lancamento(this);
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
        result = prime * result + ((categoria == null) ? 0 : categoria.hashCode());
        result = prime * result + ((pessoa == null) ? 0 : pessoa.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Lancamento other = (Lancamento) obj;
        if (codigo == null) {
            if (other.codigo != null)
                return false;
        } else if (!codigo.equals(other.codigo))
            return false;
        if (categoria == null) {
            if (other.categoria != null)
                return false;
        } else if (!categoria.equals(other.categoria))
            return false;
        if (pessoa == null) {
            if (other.pessoa != null)
                return false;
        } else if (!pessoa.equals(other.pessoa))
            return false;
        return true;
    }

}
