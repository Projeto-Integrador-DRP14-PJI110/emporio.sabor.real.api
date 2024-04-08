package org.emporio.sabor.real.api.service;

import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.emporio.sabor.real.api.model.mapper.EstoqueMapper;
import org.emporio.sabor.real.api.model.dto.EstoqueDTO;
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

    public void salvar(EstoqueDTO estoqueDTO) {
        var estoqueEntity = estoqueMapper.toEntity(estoqueDTO);
        var estoque = estoqueRepository.save(estoqueEntity);
        estoqueMapper.toDTO(estoque);
    }

    public Optional<EstoqueDTO> findById(Long id) {
        var estoque = estoqueRepository.findById(id);
        return estoque.map(estoqueMapper::toDTO);
    }

    public List<EstoqueDTO> findByCategoria(String categoria) {
        var estoque = estoqueRepository.findByCategoria(categoria);
        return estoqueMapper.toDTO(estoque);
    }

    public List<EstoqueDTO> findByProduto(String produto) {
        var estoque = estoqueRepository.findByProduto(produto);
        return estoqueMapper.toDTO(estoque);
    }

    public List<EstoqueDTO> findAll() {
        var estoque = estoqueRepository.findAll();
        return estoqueMapper.toDTO(estoque);
    }

    public void update(EstoqueDTO estoqueDTO, Long id) {
        var estoqueEntity = estoqueRepository.findById(id).orElseThrow(()
              -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Estoque não encontrado"));

        var estoque = estoqueMapper.update(estoqueEntity, estoqueDTO);

        var estoqueSave = estoqueRepository.save(estoque);
        estoqueMapper.toDTO(estoqueSave);
    }

    public void deleteById(Long id) {
        estoqueRepository.deleteById(id);
    }
}
