package com.algaworks.algamoney_api.algamoney_api.resource;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyList;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

import com.algaworks.algamoney_api.algamoney_api.dto.CategoriaDto;
import com.algaworks.algamoney_api.algamoney_api.mapper.CategoriaMapper;
import com.algaworks.algamoney_api.algamoney_api.repository.CategoriaRepository;

@ExtendWith(MockitoExtension.class)
public class CategoriaResourceTest {

    @Mock
    private CategoriaRepository categoriaRepository;

    @Mock
    private CategoriaMapper categoriaMapper;

    @Mock
    private ApplicationEventPublisher publisher;

    @InjectMocks
    private CategoriaResource categoriaResource;

    @Test
    public void testListar() {
        when(categoriaRepository.findAll()).thenReturn(Collections.emptyList());
        when(categoriaMapper.toDtoList(anyList())).thenReturn(Collections.emptyList());
        
        List<CategoriaDto> list = categoriaResource.listar();
        assertNotNull(list);
    }
}
