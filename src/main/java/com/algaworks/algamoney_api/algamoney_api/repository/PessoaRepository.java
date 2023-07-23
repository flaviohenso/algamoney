package com.algaworks.algamoney_api.algamoney_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algaworks.algamoney_api.algamoney_api.model.Pessoa;

/*
 * Interface para a entidade Pessoa, possui o endereco embebido
 */
@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    Pessoa findByCpf(String cpf);

}
