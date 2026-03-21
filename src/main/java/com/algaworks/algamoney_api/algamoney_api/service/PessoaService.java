package com.algaworks.algamoney_api.algamoney_api.service;

import com.algaworks.algamoney_api.algamoney_api.dto.PessoaUpdateDto;
import com.algaworks.algamoney_api.algamoney_api.execeptionHandler.ResourceConflictException;
import com.algaworks.algamoney_api.algamoney_api.execeptionHandler.ResourceNotFoundException;
import com.algaworks.algamoney_api.algamoney_api.mapper.PessoaMapper;
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
    private PessoaMapper pessoaMapper;

    public PessoaService(PessoaRepository pessoaRepository, PessoaMapper pessoaMapper) {
        this.pessoaRepository = pessoaRepository;
        this.pessoaMapper = pessoaMapper;
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
     * @param pessoaDto
     * @return
     */
    public Pessoa atualizar(Long id, PessoaUpdateDto pessoaDto) {
        Pessoa pessoaSalva = buscarPeloId(id);

        // Convertemos o DTO de atualização em uma entidade temporária para usar o método de domínio
        Pessoa pessoaNovosDados = new Pessoa.Builder()
            .withAtivo(pessoaDto.getAtivo())
            .email(pessoaDto.getEmail())
            .withTelefone(pessoaDto.getTelefone())
            .build();

        pessoaSalva.atualizarDados(pessoaNovosDados);

        return pessoaRepository.save(pessoaSalva);
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
    public void validarPessoaInsert(Pessoa pessoa) {
        Pessoa pessoaSalva = pessoaRepository.findByCpf(pessoa.getCpf());
        if (pessoaSalva != null && !pessoaSalva.equals(pessoa)) {
            throw new ResourceConflictException("Já existe uma pessoa cadastrada com o CPF: " + pessoa.getCpf());
        }
    }
}
