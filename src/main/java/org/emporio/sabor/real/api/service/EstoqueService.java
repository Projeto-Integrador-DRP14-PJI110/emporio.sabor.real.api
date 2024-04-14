package org.emporio.sabor.real.api.service;

import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.emporio.sabor.real.api.domain.model.Estoque;
import org.emporio.sabor.real.api.domain.mapper.EstoqueMapper;
import org.emporio.sabor.real.api.exception.NotFoundException;
import org.emporio.sabor.real.api.repository.EstoqueRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class EstoqueService {

    private final EstoqueRepository estoqueRepository;
    private final EstoqueMapper estoqueMapper;

    private static final String PRODUTO_NAO_ENCONTRADO = "Produto não encontrado";

    public void salvar(Estoque estoque) {
        var estoqueEntity = estoqueMapper.toEntity(estoque);
        var estoqueSave = estoqueRepository.save(estoqueEntity);
        estoqueMapper.toDTO(estoqueSave);
    }

    public Optional<Estoque> findById(Long id) {
        var estoque = estoqueRepository.findById(id);

        if (estoque.isEmpty()) {
            throw new NotFoundException(PRODUTO_NAO_ENCONTRADO);
        }
        return estoque.map(estoqueMapper::toDTO);
    }

    public List<Estoque> findByCategoria(String categoria) {
        var estoque = estoqueRepository.findByCategoria(categoria);

        if (estoque.isEmpty()) {
            throw new NotFoundException("Categoria não encontrada");
        }
        return estoqueMapper.toModel(estoque);
    }

    public List<Estoque> findByProduto(String produto) {
        var estoque = estoqueRepository.findByProduto(produto);

        if (estoque.isEmpty()) {
            throw new NotFoundException(PRODUTO_NAO_ENCONTRADO);
        }
        return estoqueMapper.toModel(estoque);
    }

    public List<Estoque> findAll() {
        var estoque = estoqueRepository.findAll();
        return estoqueMapper.toModel(estoque);
    }

    public void update(Estoque estoque, Long id) {
        var estoqueEntity = estoqueRepository.findById(id).orElseThrow(()
              -> new NotFoundException(PRODUTO_NAO_ENCONTRADO));

        var estoqueUpdate = estoqueMapper.update(estoqueEntity, estoque);

        var estoqueSave = estoqueRepository.save(estoqueUpdate);
        estoqueMapper.toDTO(estoqueSave);
    }

    public void inactivate(Long id) {
        var estoqueEntity = estoqueRepository.findById(id).orElseThrow(()
              -> new NotFoundException(PRODUTO_NAO_ENCONTRADO));

        estoqueEntity.setProdutoDisponivel(false);
        estoqueRepository.save(estoqueEntity);
    }
}
