package org.emporio.sabor.real.api.domain.mapper;

import org.emporio.sabor.real.api.domain.entity.CredentialEntity;
import org.emporio.sabor.real.api.domain.model.Credential;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CredentialMapper {

    CredentialMapper CREDENTIAL_MAPPER = Mappers.getMapper(CredentialMapper.class);

    CredentialEntity toEntity(Credential credential);

    Credential toModel(CredentialEntity credentialEntity);

    @Mapping(target = "email", source = "email")
    @Mapping(target = "password", source = "password")
    CredentialEntity toEntity(String email, String password);
}
