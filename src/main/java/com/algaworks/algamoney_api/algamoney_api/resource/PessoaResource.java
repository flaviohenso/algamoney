package com.algaworks.algamoney_api.algamoney_api.resource;

import com.algaworks.algamoney_api.algamoney_api.dto.PessoaUpdateDto;
import com.algaworks.algamoney_api.algamoney_api.model.Pessoa;
import com.algaworks.algamoney_api.algamoney_api.service.PessoaService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;




/*
 * Controller para a entidade Pessoa
 * possui endpoints para listar, criar, atualizar e deletar
 */
@RestController
@RequestMapping("/pessoa")
public class PessoaResource {

    private PessoaService pessoaService;

    public PessoaResource(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    /**
     * End point para listar todas as pessoas
     *
     * @return lista de pessoas
     */
    @GetMapping(path = "/listar")
    public List<Pessoa> listar() {
        return pessoaService.listar();
    }

    /**
     * End point para atualizar uma pessoa
     *
     * @param id
     * @param pessoa
     * @return
     */
    @PutMapping(path = "/atualizar/{id}")
    public ResponseEntity<Pessoa> atualizar(@PathVariable Long id, @Valid @RequestBody PessoaUpdateDto pessoa) {
        Pessoa pessoaSalva = pessoaService.atualizar(id, pessoa);
        return ResponseEntity.ok(pessoaSalva);
    }

    /**
     * End point para buscar uma pessoa pelo id
     */
    @GetMapping(path = "/listar/{id}")
    public ResponseEntity<Pessoa> buscarPeloId(@PathVariable Long id) {
        Pessoa pessoa = pessoaService.buscarPeloId(id);
        return ResponseEntity.ok(pessoa);
    }

    /**
     * End point para deletar uma pessoa
     *
     * @param id
     */
    @DeleteMapping(path = "/remover/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        pessoaService.remover(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * End point para criar uma pessoa
     */
    @PostMapping(path = "/criar")
    public ResponseEntity<Pessoa> criar(@Valid @RequestBody Pessoa pessoa) {

        pessoaService.validarPessoaInsert(pessoa);
        Pessoa pessoaSalva = pessoaService.criar(pessoa);
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaSalva);
    }

}