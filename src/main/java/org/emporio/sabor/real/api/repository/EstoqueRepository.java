package org.emporio.sabor.real.api.repository;

import java.util.List;
import java.util.Optional;
import org.emporio.sabor.real.api.model.entity.EstoqueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstoqueRepository extends JpaRepository<EstoqueEntity, Long> {

    List<EstoqueEntity> findByCategoria(String categoria);

    Optional<EstoqueEntity> findByProduto(String produto);
}
