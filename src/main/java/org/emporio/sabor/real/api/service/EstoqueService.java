package org.emporio.sabor.real.api.service;

import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.emporio.sabor.real.api.domain.model.Estoque;
import org.emporio.sabor.real.api.domain.mapper.EstoqueMapper;
import org.emporio.sabor.real.api.repository.EstoqueRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
@Transactional
public class EstoqueService {

    private final EstoqueRepository estoqueRepository;
    private final EstoqueMapper estoqueMapper;

    public void salvar(Estoque estoque) {
        var estoqueEntity = estoqueMapper.toEntity(estoque);
        var estoqueSave = estoqueRepository.save(estoqueEntity);
        estoqueMapper.toDTO(estoqueSave);
    }

    public Optional<Estoque> findById(Long id) {
        var estoque = estoqueRepository.findById(id);
        return estoque.map(estoqueMapper::toDTO);
    }

    public List<Estoque> findByCategoria(String categoria) {
        var estoque = estoqueRepository.findByCategoria(categoria);
        return estoqueMapper.toModel(estoque);
    }

    public List<Estoque> findByProduto(String produto) {
        var estoque = estoqueRepository.findByProduto(produto);
        return estoqueMapper.toModel(estoque);
    }

    public List<Estoque> findAll() {
        var estoque = estoqueRepository.findAll();
        return estoqueMapper.toModel(estoque);
    }

    public void update(Estoque estoque, Long id) {
        var estoqueEntity = estoqueRepository.findById(id).orElseThrow(()
              -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Estoque não encontrado"));

        var estoqueUpdate = estoqueMapper.update(estoqueEntity, estoque);

        var estoqueSave = estoqueRepository.save(estoqueUpdate);
        estoqueMapper.toDTO(estoqueSave);
    }

    public void inactivate(Long id) {
        var estoqueEntity = estoqueRepository.findById(id).orElseThrow(()
              -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Estoque não encontrado"));

        estoqueEntity.setProdutoDisponivel(false);
        estoqueRepository.save(estoqueEntity);
    }
}
