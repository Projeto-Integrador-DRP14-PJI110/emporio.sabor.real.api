package org.emporio.sabor.real.api.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.emporio.sabor.real.api.model.dto.EstoqueDTO;
import org.emporio.sabor.real.api.service.EstoqueService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
        estoqueService.salvar(estoqueDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Optional<EstoqueDTO>> findById(@PathVariable Long id) {
        var estoque = estoqueService.findById(id);
        return ResponseEntity.ok(estoque);
    }

    @GetMapping("/produto/{produto}")
    public ResponseEntity<Optional<EstoqueDTO>> findByProduto(@PathVariable String produto) {
        var estoque = estoqueService.findByProduto(produto);
        return ResponseEntity.ok(estoque);
    }

    @GetMapping
    public ResponseEntity<List<EstoqueDTO>> findAll() {
        var estoque = estoqueService.findAll();
        return ResponseEntity.ok(estoque);
    }

    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<EstoqueDTO>> findByCategoria(@PathVariable String categoria) {
        var estoque = estoqueService.findByCategoria(categoria);
        return ResponseEntity.ok(estoque);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        estoqueService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
