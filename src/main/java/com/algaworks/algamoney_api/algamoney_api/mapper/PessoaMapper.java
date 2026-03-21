package com.algaworks.algamoney_api.algamoney_api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.algaworks.algamoney_api.algamoney_api.dto.PessoaDto;
import com.algaworks.algamoney_api.algamoney_api.model.Pessoa;

@Component
public class PessoaMapper {

    public Pessoa toEntity(PessoaDto dto) {
        if (dto == null) {
            return null;
        }
        return new Pessoa.Builder()
                .nome(dto.getNome())
                .cpf(dto.getCpf())
                .email(dto.getEmail())
                .withTelefone(dto.getTelefone())
                .withAtivo(dto.getAtivo())
                .endereco(dto.getEndereco())
                .build();
    }

    public PessoaDto toDto(Pessoa entity) {
        if (entity == null) {
            return null;
        }
        return new PessoaDto.Builder()
                .nome(entity.getNome())
                .cpf(entity.getCpf())
                .email(entity.getEmail())
                .withTelefone(entity.getTelefone())
                .withAtivo(entity.getAtivo())
                .endereco(entity.getEndereco())
                .build();
    }

    public List<PessoaDto> toDtoList(List<Pessoa> entities) {
        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
