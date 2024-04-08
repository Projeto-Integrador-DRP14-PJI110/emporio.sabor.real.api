package org.emporio.sabor.real.api.model.mapper;

import java.util.List;
import org.emporio.sabor.real.api.model.dto.EstoqueDTO;
import org.emporio.sabor.real.api.model.dto.EstoqueUpdateDTO;
import org.emporio.sabor.real.api.model.entity.EstoqueEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface EstoqueMapper {

    EstoqueEntity toEntity(EstoqueDTO estoqueDTO);

    EstoqueDTO toDTO(EstoqueEntity estoqueEntity);

    List<EstoqueDTO> toDTO(List<EstoqueEntity> estoqueEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    EstoqueEntity update(@MappingTarget EstoqueEntity estoqueEntity, EstoqueDTO estoqueDTO);

}
