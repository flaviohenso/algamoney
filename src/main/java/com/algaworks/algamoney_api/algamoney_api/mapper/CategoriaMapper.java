package com.algaworks.algamoney_api.algamoney_api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.algaworks.algamoney_api.algamoney_api.dto.CategoriaDto;
import com.algaworks.algamoney_api.algamoney_api.model.Categoria;

@Component
public class CategoriaMapper {

    public Categoria toEntity(CategoriaDto dto) {
        if (dto == null) {
            return null;
        }
        return new Categoria.Builder()
                .nome(dto.getNome())
                .descricao(dto.getDescricao())
                .build();
    }

    public CategoriaDto toDto(Categoria entity) {
        if (entity == null) {
            return null;
        }
        return new CategoriaDto.Builder()
                .nome(entity.getNome())
                .descricao(entity.getDescricao())
                .build();
    }

    public List<CategoriaDto> toDtoList(List<Categoria> entities) {
        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
