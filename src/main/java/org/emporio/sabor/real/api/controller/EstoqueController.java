package org.emporio.sabor.real.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.emporio.sabor.real.api.model.dto.EstoqueDTO;
import org.emporio.sabor.real.api.service.EstoqueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "api/v1/estoque", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Estoque Emporio Sabor Real", description = "Estoque Emporio Sabor Real API")
public class EstoqueController {

    private final EstoqueService estoqueService;

    @PostMapping
    @Operation(summary = "Salvar um produto no estoque", description = "Salva um produto no estoque", tags = {
          "Estoque Emporio Sabor Real"})
    @ApiResponses(value = {
          @ApiResponse(responseCode = "201", description = "Produto criado com sucesso")})
    public ResponseEntity<EstoqueDTO> salvar(@RequestBody EstoqueDTO estoqueDTO) {
        estoqueService.salvar(estoqueDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    @Operation(summary = "Buscar todos os produtos do estoque", description = "Busca todos os produtos cadastrados no estoque", tags = {
          "Estoque Emporio Sabor Real"})
    @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Retorna todos os produtos do estoque")})
    public ResponseEntity<List<EstoqueDTO>> findAll() {
        var estoque = estoqueService.findAll();
        return ResponseEntity.ok(estoque);
    }

    @GetMapping("/id/{id}")
    @Operation(summary = "Buscar um produto pelo id", description = "Busca um produto cadastrado no estoque pelo id", tags = {
          "Estoque Emporio Sabor Real"})
    @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Retorna o produto do estoque")})
    public ResponseEntity<Optional<EstoqueDTO>> findById(@PathVariable Long id) {
        var estoque = estoqueService.findById(id);
        return ResponseEntity.ok(estoque);
    }

    @GetMapping("/produto/{produto}")
    @Operation(summary = "Buscar um produto pelo nome", description = "Busca um produto cadastrado no estoque pelo nome", tags = {
          "Estoque Emporio Sabor Real"})
    @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Retorna o produto do estoque")})
    public ResponseEntity<List<EstoqueDTO>> findByProduto(@PathVariable String produto) {
        var estoque = estoqueService.findByProduto(produto);
        return ResponseEntity.ok(estoque);
    }

    @GetMapping("/categoria/{categoria}")
    @Operation(summary = "Buscar um produto pela categoria", description = "Busca um produto cadastrado no estoque pela categoria", tags = {
          "Estoque Emporio Sabor Real"})
    @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Retorna o produto do estoque")})
    public ResponseEntity<List<EstoqueDTO>> findByCategoria(@PathVariable String categoria) {
        var estoque = estoqueService.findByCategoria(categoria);
        return ResponseEntity.ok(estoque);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Atualizar um produto no estoque", description = "Atualiza um produto no estoque", tags = {
          "Estoque Emporio Sabor Real"})
    @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso")})
    public ResponseEntity<EstoqueDTO> update(@RequestBody EstoqueDTO estoqueDTO, @PathVariable Long id) {
        estoqueService.update(estoqueDTO, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar um produto do estoque", description = "Deleta um produto do estoque", tags = {
          "Estoque Emporio Sabor Real"})
    @ApiResponses(value = {
          @ApiResponse(responseCode = "204", description = "Produto deletado com sucesso")})
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        estoqueService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}