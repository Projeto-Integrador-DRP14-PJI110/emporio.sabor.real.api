package org.emporio.sabor.real.api.domain.mapper;

import org.emporio.sabor.real.api.domain.dto.RecoveryEmailDTO;
import org.emporio.sabor.real.api.domain.model.RecoveryEmail;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EmailMapper {

    EmailMapper EMAIL_MAPPER = Mappers.getMapper(EmailMapper.class);

    RecoveryEmail toRecoveryEmail(RecoveryEmailDTO recoveryEmailDTO);
    RecoveryEmailDTO toRecoveryEmailDTO(RecoveryEmail recoveryEmail);
}
