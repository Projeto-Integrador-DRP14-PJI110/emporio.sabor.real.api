package org.emporio.sabor.real.api.model;

import java.util.List;
import org.emporio.sabor.real.api.model.dto.EstoqueDTO;
import org.emporio.sabor.real.api.model.entity.EstoqueEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EstoqueMapper {

    EstoqueEntity toEntity(EstoqueDTO estoqueDTO);

    EstoqueDTO toDTO(EstoqueEntity estoqueEntity);

    List<EstoqueDTO> toDTO(List<EstoqueEntity> estoqueEntity);

}
