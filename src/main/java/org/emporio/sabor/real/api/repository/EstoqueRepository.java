package org.emporio.sabor.real.api.repository;

import java.util.List;
import org.emporio.sabor.real.api.domain.entity.EstoqueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EstoqueRepository extends JpaRepository<EstoqueEntity, Long> {

    @Query(value = "SELECT e FROM EstoqueEntity e WHERE lower(e.categoria) like concat('%', :categoria, '%' )")
    List<EstoqueEntity> findByCategoria(String categoria);

    @Query(value = "SELECT e FROM EstoqueEntity e WHERE lower(e.produto) like concat('%', :produto, '%' )")
    List<EstoqueEntity> findByProduto(String produto);
}
