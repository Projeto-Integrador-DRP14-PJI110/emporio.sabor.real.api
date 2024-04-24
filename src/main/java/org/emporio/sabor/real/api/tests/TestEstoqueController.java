package org.emporio.sabor.real.api.tests;

import org.emporio.sabor.real.api.controller.EstoqueController;
import org.emporio.sabor.real.api.model.dto.EstoqueDTO;
import org.emporio.sabor.real.api.service.EstoqueService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;


public class TestEstoqueController {

        @InjectMocks
        private EstoqueController estoqueController;

        @Mock
        private EstoqueService estoqueService;

        @BeforeEach
        public void setup(){
            MockitoAnnotations.openMocks(this);
        }

        @Test
        public void testSalvarUsingMockitoDoReturn() {
            EstoqueDTO estoqueDTO = new EstoqueDTO();
    
            doReturn(estoqueDTO).when(estoqueService)
                                 .salvar(Mockito.any(EstoqueDTO.class));
    
            ResponseEntity<EstoqueDTO> response = estoqueController.salvar(estoqueDTO);
    
            assertEquals(200, response.getStatusCode());
            assertEquals(estoqueDTO, response.getBody());
        }
    }
