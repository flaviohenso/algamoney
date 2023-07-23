package com.algaworks.algamoney_api.algamoney_api.resource;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.algaworks.algamoney_api.algamoney_api.dto.CategoriaDto;
import com.algaworks.algamoney_api.algamoney_api.execeptionHandler.ResourceNotFoundException;
import com.algaworks.algamoney_api.algamoney_api.model.Categoria;
import com.algaworks.algamoney_api.algamoney_api.repository.CategoriaRepository;

@RestController
@RequestMapping("/categoria")
@Validated
public class CategoriaResource {

    private CategoriaRepository categoriaRepository;

    public CategoriaResource(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    /**
     * Método para retorna uma lista de categorias, caso não exista retorna uma lista vazia
     * @return List<CategoriaDto>
     */
    @GetMapping(path = "/listar")
    public List<CategoriaDto> listar() {
        return CategoriaDto.toCategoriaDtoList(categoriaRepository.findAll());
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

        return ResponseEntity.ok(new CategoriaDto.Builder()
                .nome(categoria.getNome())
                .descricao(categoria.getDescricao())
                .build());
    }

    /**
     * Método post para salvar uma categoria
     * @param categoriaDto CategoriaDto
     * @param response HttpServletResponse
     * @return ResponseEntity<CategoriaDto> retorna o status 201 created e o body com a categoria salva e o link para acessar a categoria salva
     */
    @PostMapping(path = "/criar")
    public ResponseEntity<CategoriaDto> criar(@RequestBody @Valid CategoriaDto categoriaDto , HttpServletResponse response ) {
        Categoria categoriaSalva = categoriaRepository.save(categoriaDto.toCategoria());

        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/categoria/listar/{codigo}")
                .buildAndExpand(categoriaSalva.getCodigo()).toUri();

        return ResponseEntity.created(uri).body(categoriaDto);
    }
}
