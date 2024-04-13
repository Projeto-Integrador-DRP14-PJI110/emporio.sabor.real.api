package org.emporio.sabor.real.api.domain.mapper;

import java.util.List;
import java.util.Optional;
import org.emporio.sabor.real.api.domain.model.Estoque;
import org.emporio.sabor.real.api.domain.dto.EstoqueRequestDTO;
import org.emporio.sabor.real.api.domain.dto.EstoqueResponseDTO;
import org.emporio.sabor.real.api.domain.dto.EstoqueUpdateDTO;
import org.emporio.sabor.real.api.domain.entity.EstoqueEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface EstoqueMapper {

    EstoqueEntity toEntity(Estoque estoque);

    Estoque toDTO(EstoqueEntity estoqueEntity);

    List<Estoque> toModel(List<EstoqueEntity> estoqueEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    EstoqueEntity update(@MappingTarget EstoqueEntity estoqueEntity, Estoque estoque);

    Estoque create(EstoqueRequestDTO estoqueRequestDTO);

    Estoque update(EstoqueUpdateDTO estoqueUpdateDTO);

    List<EstoqueResponseDTO> toDTO(List<Estoque> estoque);

    EstoqueResponseDTO toResponse(Estoque estoque);
}
