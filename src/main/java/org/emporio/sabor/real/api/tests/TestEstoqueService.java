package org.emporio.sabor.real.api.tests;

import org.emporio.sabor.real.api.domain.mapper.EstoqueMapper;
import org.emporio.sabor.real.api.repository.EstoqueRepository;
import org.emporio.sabor.real.api.service.EstoqueService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.*;


public class TestEstoqueService {

    @InjectMocks
    private EstoqueService estoqueService;

    @Mock
    private EstoqueRepository estoqueRepository;

    @Mock
    private org.emporio.sabor.real.api.model.mapper.EstoqueMapper EstoqueMapper;

    @BeforeEach
    public void setup(){
        estoqueService = new EstoqueService(estoqueRepository, EstoqueMapper);
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSalvar(){
        Estoque estoque = new Estoque();
        when (EstoqueMapper.toEntity(estoque)).thenReturn(estoque);
        when(estoqueRepository.save(estoque)).thenReturn(estoque);
        estoqueService.salvar(estoque);
        verify(estoqueRepository, times(1)).findById(id);
    }
    
}
