package com.algaworks.algamoney_api.algamoney_api.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algamoney_api.algamoney_api.dto.PessoaDto;
import com.algaworks.algamoney_api.algamoney_api.dto.PessoaUpdateDto;
import com.algaworks.algamoney_api.algamoney_api.evento.RecursoCriadoEvent;
import com.algaworks.algamoney_api.algamoney_api.evento.RecursoRemovidoEvent;
import com.algaworks.algamoney_api.algamoney_api.model.Pessoa;
import com.algaworks.algamoney_api.algamoney_api.service.PessoaService;

/*
 * Controller para a entidade Pessoa
 * possui endpoints para listar, criar, atualizar e deletar
 */
@RestController
@RequestMapping("/pessoa")
public class PessoaResource {

    private PessoaService pessoaService;
    private ApplicationEventPublisher publisher;

    public PessoaResource(PessoaService pessoaService, ApplicationEventPublisher publisher) {
        this.pessoaService = pessoaService;
        this.publisher = publisher;
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
    public ResponseEntity<PessoaDto> atualizar(@PathVariable Long id, @Valid @RequestBody PessoaUpdateDto pessoa) {
        PessoaDto pessoaDto = new Pessoa().toPessoaDto(pessoaService.atualizar(id, pessoa));
        return ResponseEntity.ok(pessoaDto);
    }

    /**
     * End point para buscar uma pessoa pelo id
     */
    @GetMapping(path = "/listar/{id}")
    public ResponseEntity<PessoaDto> buscarPeloId(@PathVariable Long id) {
        PessoaDto pessoaDto = new Pessoa().toPessoaDto(pessoaService.buscarPeloId(id));
        return ResponseEntity.ok(pessoaDto);
    }

    /**
     * End point para deletar uma pessoa
     *
     * @param id
     */
    @DeleteMapping(path = "/remover/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id, HttpServletResponse response) {
        pessoaService.remover(id);

        publisher.publishEvent(new RecursoRemovidoEvent(this, response));
    }

    /**
     * End point para criar uma pessoa
     */
    @PostMapping(path = "/criar")
    public PessoaDto criar(@Valid @RequestBody PessoaDto pessoaDto, HttpServletResponse response) {
        Pessoa pessoa = pessoaDto.toPessoa(pessoaDto);
        pessoaService.validarPessoaInsert(pessoa);
        Pessoa pessoaSalva = pessoaService.criar(pessoa);

        publisher.publishEvent(new RecursoCriadoEvent(this, response, pessoaSalva.getId()));

        return pessoaDto;

    }

}