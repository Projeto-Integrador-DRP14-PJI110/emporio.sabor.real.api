package org.emporio.sabor.real.api.repository;

import java.util.List;
import java.util.Optional;
import org.emporio.sabor.real.api.model.entity.EstoqueEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstoqueRepository extends CrudRepository<EstoqueEntity, Long> {

    List<EstoqueEntity> findByCategoria(String categoria);

    Optional<EstoqueEntity> findByProduto(String produto);
}
