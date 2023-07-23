package com.algaworks.algamoney_api.algamoney_api.service;

import com.algaworks.algamoney_api.algamoney_api.dto.PessoaUpdateDto;
import com.algaworks.algamoney_api.algamoney_api.execeptionHandler.ResourceConflictException;
import com.algaworks.algamoney_api.algamoney_api.execeptionHandler.ResourceNotFoundException;
import com.algaworks.algamoney_api.algamoney_api.model.Pessoa;
import com.algaworks.algamoney_api.algamoney_api.repository.PessoaRepository;
import java.util.List;
import org.springframework.stereotype.Service;




/**
 * Classe de serviço para a entidade Pessoa
 */
@Service
public class PessoaService {

    private PessoaRepository pessoaRepository;

    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    /**
     * Método para listar todas as pessoas
     *
     * @return lista de pessoas
     */
    public List<Pessoa> listar() {
        return pessoaRepository.findAll();
    }

    /**
     * Método para atualizar uma pessoa
     *
     * @param id
     * @param pessoa
     * @return
     */
    public Pessoa atualizar(Long id, PessoaUpdateDto pessoa) {
        Pessoa pessoaSalva = buscarPeloId(id);
        atualizaPessoa(pessoa, pessoaSalva);
        return pessoaRepository.save(pessoaSalva);
    }

    /**
     * Método para atualizar os atributos de uma pessoa
     *
     * @param pessoa
     * @param pessoaSalva
     */
    //TODO: Mover essa lógica para a classe Pessoa
    private void atualizaPessoa(PessoaUpdateDto pessoa, Pessoa pessoaSalva) {
        if(pessoa.getAtivo() != null){
            pessoaSalva.setAtivo(pessoa.getAtivo());
        }
        if(pessoa.getEmail() != null && !pessoa.getEmail().isEmpty()){
            pessoaSalva.setEmail(pessoa.getEmail());
        }
        if(pessoa.getTelefone() != null && !pessoa.getTelefone().isEmpty()){
            pessoaSalva.setTelefone(pessoa.getTelefone());
        }
    }

    /**
     * Método para buscar uma pessoa pelo id que compoe a classe pessoaKey
     *
     */
    public Pessoa buscarPeloId(Long id) {
        return pessoaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pessoa não encontrada para o codigo: " + id + "!"));
    }


    /**
     * Método para deletar uma pessoa
     *
     * @param id
     */
    public void remover(Long id) {
        this.buscarPeloId(id); // verifica se a pessoa existe
        pessoaRepository.deleteById(id);
    }

    /*
     * Método para criar uma pessoa
     */
    public Pessoa criar(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    /**
     * Método para validar se a pessoa já existe
     * @param pessoa
     */
    //TODO: Mover essa validaão para a classe Pessoa
    public void validarPessoaInsert(Pessoa pessoa) {
        Pessoa pessoaSalva = pessoaRepository.findByCpf(pessoa.getCpf());
        if (pessoaSalva != null && !pessoaSalva.equals(pessoa)) {
            throw new ResourceConflictException("Já existe uma pessoa cadastrada com o CPF: " + pessoa.getCpf());
        }
    }
}
