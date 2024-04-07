package org.emporio.sabor.real.api.service;

import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.emporio.sabor.real.api.model.EstoqueMapper;
import org.emporio.sabor.real.api.model.dto.EstoqueDTO;
import org.emporio.sabor.real.api.repository.EstoqueRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class EstoqueService {

    private final EstoqueRepository estoqueRepository;
    private final EstoqueMapper estoqueMapper;

    public EstoqueDTO salvar(EstoqueDTO estoqueDTO) {
        var estoqueEntity = estoqueMapper.toEntity(estoqueDTO);
        var estoque = estoqueRepository.save(estoqueEntity);
        return estoqueMapper.toDTO(estoque);
    }

    public Optional<EstoqueDTO> findById(Long id) {
        var estoque = estoqueRepository.findById(id);
        return estoque.map(estoqueMapper::toDTO);
    }

    public void deleteById(Long id) {
        estoqueRepository.deleteById(id);
    }

    public List<EstoqueDTO> findByCategoria(String categoria) {
        var estoque = estoqueRepository.findByCategoria(categoria);
        return estoqueMapper.toDTO(estoque);
    }

    public Optional<EstoqueDTO> findByProduto(String produto) {
        var estoque = estoqueRepository.findByProduto(produto);
        return estoque.map(estoqueMapper::toDTO);
    }

    public List<EstoqueDTO> findAll() {
        var estoque = estoqueRepository.findAll();
        return estoqueMapper.toDTO(estoque);
    }
}
