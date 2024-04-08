package org.emporio.sabor.real.api.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.AllArgsConstructor;
import org.emporio.sabor.real.api.model.dto.EstoqueDTO;
import org.emporio.sabor.real.api.service.EstoqueService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(value = "api/v1/estoque", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Estoque Emporio Sabor Real", description = "Estoque Emporio Sabor Real API")
public class EstoqueController {

    private final EstoqueService estoqueService;

    @PostMapping
    public ResponseEntity<EstoqueDTO> salvar(@RequestBody EstoqueDTO estoqueDTO) {
        var estoque = estoqueService.salvar(estoqueDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/id")
    public ResponseEntity<EstoqueDTO> findById(Long id) {
        var estoque = estoqueService.findById(id);
        return ResponseEntity.ok(estoque.orElse(null));
    }

    @GetMapping("/produto")
    public ResponseEntity<EstoqueDTO> findByProduto(String produto) {
        var estoque = estoqueService.findByProduto(produto);
        return ResponseEntity.ok(estoque.orElse(null));
    }

    @GetMapping
    public ResponseEntity<List<EstoqueDTO>> findAll() {
        var estoque = estoqueService.findAll();
        return ResponseEntity.ok(estoque);
    }

    @GetMapping("/categoria")
    public ResponseEntity<List<EstoqueDTO>> findByCategoria(String categoria) {
        var estoque = estoqueService.findByCategoria(categoria);
        return ResponseEntity.ok(estoque);
    }

    @DeleteMapping("/id")
    public ResponseEntity<Void> deleteById(Long id) {
        estoqueService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
