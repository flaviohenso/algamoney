package com.algaworks.algamoney_api.algamoney_api.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "pessoa")
public class Pessoa {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY) // gera o id automaticamente
private Long id;

@NotBlank
private String nome;

@Column(unique = true)
private String cpf;

private String email;

private String telefone;

private Boolean ativo;

@Embedded
private Endereco endereco;

public Pessoa() {
}

/**
 * getter e setter
 */

 public String getCpf() {
    return cpf;
}

public void setCpf(String cpf) {
    this.cpf = cpf;
}

public Boolean getAtivo() {
    return ativo;
}

public String getEmail() {
    return email;
}

public Endereco getEndereco() {
    return endereco;
}

public Long getId() {
    return id;
}

public String getNome() {
    return nome;
}

public String getTelefone() {
    return telefone;
}

public void setAtivo(Boolean ativo) {
    this.ativo = ativo;
}

public void setEmail(String email) {
    this.email = email;
}

public void setEndereco(Endereco endereco) {
    this.endereco = endereco;
}

public void setId(Long id) {
    this.id = id;
}

public void setNome(String nome) {
    this.nome = nome;
}

public void setTelefone(String telefone) {
    this.telefone = telefone;
}

@Override
public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((nome == null) ? 0 : nome.hashCode());
    result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
    result = prime * result + ((email == null) ? 0 : email.hashCode());
    result = prime * result + ((telefone == null) ? 0 : telefone.hashCode());
    result = prime * result + ((ativo == null) ? 0 : ativo.hashCode());
    result = prime * result + ((endereco == null) ? 0 : endereco.hashCode());
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
    Pessoa other = (Pessoa) obj;
    if (id == null) {
        if (other.id != null)
            return false;
    } else if (!id.equals(other.id))
        return false;
    if (nome == null) {
        if (other.nome != null)
            return false;
    } else if (!nome.equals(other.nome))
        return false;
    if (cpf == null) {
        if (other.cpf != null)
            return false;
    } else if (!cpf.equals(other.cpf))
        return false;
    if (email == null) {
        if (other.email != null)
            return false;
    } else if (!email.equals(other.email))
        return false;
    if (telefone == null) {
        if (other.telefone != null)
            return false;
    } else if (!telefone.equals(other.telefone))
        return false;
    if (ativo == null) {
        if (other.ativo != null)
            return false;
    } else if (!ativo.equals(other.ativo))
        return false;
    if (endereco == null) {
        if (other.endereco != null)
            return false;
    } else if (!endereco.equals(other.endereco))
        return false;
    return true;
}


/* Builder */
public static final class PessoaBuilder {
        private Long id;
        private String nome;
        private String cpf;
        private String email;
        private String telefone;
        private Boolean ativo;
        private Endereco endereco;

        private PessoaBuilder() {
        }

        public static PessoaBuilder aPessoa() {
            return new PessoaBuilder();
        }

        public PessoaBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public PessoaBuilder withNome(String nome) {
            this.nome = nome;
            return this;
        }

        public PessoaBuilder withCpf(String cpf) {
            this.cpf = cpf;
            return this;
        }

        public PessoaBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        public PessoaBuilder withTelefone(String telefone) {
            this.telefone = telefone;
            return this;
        }

        public PessoaBuilder withAtivo(Boolean ativo) {
            this.ativo = ativo;
            return this;
        }

        public PessoaBuilder withEndereco(Endereco endereco) {
            this.endereco = endereco;
            return this;
        }

        public Pessoa build() {
            Pessoa pessoa = new Pessoa();
            pessoa.setId(id);
            pessoa.setNome(nome);
            pessoa.setCpf(cpf);
            pessoa.setEmail(email);
            pessoa.setTelefone(telefone);
            pessoa.setAtivo(ativo);
            pessoa.setEndereco(endereco);
            return pessoa;
        }

    }
}