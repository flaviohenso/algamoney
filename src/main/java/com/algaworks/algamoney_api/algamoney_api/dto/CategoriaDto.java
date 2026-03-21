package com.algaworks.algamoney_api.algamoney_api.dto;

import com.algaworks.algamoney_api.algamoney_api.model.Categoria;
import java.util.List;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
// Add the new import statement
import jakarta.validation.*;

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
