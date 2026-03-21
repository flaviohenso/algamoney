package com.algaworks.algamoney_api.algamoney_api.resource;

import java.net.URI;
import java.util.List;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algamoney_api.algamoney_api.dto.CategoriaDto;
import com.algaworks.algamoney_api.algamoney_api.evento.RecursoCriadoEvent;
import com.algaworks.algamoney_api.algamoney_api.execeptionHandler.ResourceNotFoundException;
import com.algaworks.algamoney_api.algamoney_api.mapper.CategoriaMapper;
import com.algaworks.algamoney_api.algamoney_api.model.Categoria;
import com.algaworks.algamoney_api.algamoney_api.repository.CategoriaRepository;

@RestController
@RequestMapping("/categoria")
@Validated
public class CategoriaResource {

    private CategoriaRepository categoriaRepository;
    private CategoriaMapper categoriaMapper;
    private ApplicationEventPublisher publisher;

    public CategoriaResource(CategoriaRepository categoriaRepository, CategoriaMapper categoriaMapper, ApplicationEventPublisher publisher) {
        this.categoriaRepository = categoriaRepository;
        this.categoriaMapper = categoriaMapper;
        this.publisher = publisher;
    }

    /**
     * Método para retorna uma lista de categorias, caso não exista retorna uma lista vazia
     * @return List<CategoriaDto>
     */
    @GetMapping(path = "/listar")
    public List<CategoriaDto> listar() {
        return categoriaMapper.toDtoList(categoriaRepository.findAll());
    }

    /**
     * Método para retorna uma categoria, caso a categoria não exista dispara uma exception do tipo ResourceNotFoundException
     * @param codigo codigo da categoria
     * @return CategoriaDto
     */
    @GetMapping(path = "/listar/{codigo}")
    public ResponseEntity<CategoriaDto> buscarPeloCodigo(@PathVariable Long codigo) {
        Categoria categoria = categoriaRepository.findById(codigo)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada para o codigo: " + codigo + "!"));

        return ResponseEntity.ok(categoriaMapper.toDto(categoria));
    }

    /**
     * Método post para salvar uma categoria
     * @param categoriaDto CategoriaDto
     * @param response HttpServletResponse
     * @return ResponseEntity<CategoriaDto> retorna o status 201 created e o body com a categoria salva e o link para acessar a categoria salva
     */
    @PostMapping(path = "/criar")
    public ResponseEntity<CategoriaDto> criar(@RequestBody @Valid CategoriaDto categoriaDto , HttpServletResponse response ) {
        Categoria categoriaSalva = categoriaRepository.save(categoriaMapper.toEntity(categoriaDto));

        publisher.publishEvent(new RecursoCriadoEvent(this, response, categoriaSalva.getCodigo(), "/categoria/listar/"));

        return ResponseEntity.status(201).body(categoriaDto);
    }
}
