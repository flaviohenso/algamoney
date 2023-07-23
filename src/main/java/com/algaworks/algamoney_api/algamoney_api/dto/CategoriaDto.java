package com.algaworks.algamoney_api.algamoney_api.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.algaworks.algamoney_api.algamoney_api.model.Categoria;

public class CategoriaDto {

    @NotBlank
    @Size(min = 3, max = 50)
    private String nome;

    @NotBlank
    @Size(min = 10, max = 100)
    private String descricao;

    public CategoriaDto() {
    }

    /*
     * Construtor para ser utilizado pelo spring boot
     */
    public CategoriaDto(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    /*
     * construtor para o builder
     */
    private CategoriaDto(Builder builder) {
        this.nome = builder.nome;
        this.descricao = builder.descricao;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao(){
        return this.descricao;
    }

    /**
     * Método para converter um objeto CategoriaDto para um objeto Categoria
     * @return Categoria
     */
    public Categoria toCategoria() {
        return new Categoria.Builder()
                .nome(this.nome)
                .descricao(this.descricao)
                .build();
    }

    /**
     * Método para converter um objeto Categoria para um objeto CategoriaDto
     * @param categoria
     * @return CategoriaDto
     */
    public static CategoriaDto toCategoriaDto(Categoria categoria) {
        return new CategoriaDto.Builder()
                .nome(categoria.getNome())
                .descricao(categoria.getDescricao())
                .build();
        }

    /**
     * Método para converter um Lista de Categoria para um Lista de CategoriaDto
     * @param List<categoria>
     * @return List<CategoriaDto>
     */
    public static List<CategoriaDto> toCategoriaDtoList(List<Categoria> categoria) {
        return categoria.stream().map(CategoriaDto::toCategoriaDto).collect(java.util.stream.Collectors.toList());
    }

    /*
     * Builder para a classe CategoriaDto
     */
    public static class Builder {
        @NotNull
        private String nome;
        private String descricao;

        /**
         * @param nome String
         * @return Builder
         */
        public Builder nome(String nome) {
            this.nome = nome;
            return this;
        }


        /**
         * @param descricao String
         * @return  Builder
         */
        public Builder descricao(String descricao) {
            this.descricao = descricao;
            return this;
        }

        /**
         * @return CategoriaDto com os atributos setados
         */
        public CategoriaDto build() {
            return new CategoriaDto(this);
        }
    }
}
