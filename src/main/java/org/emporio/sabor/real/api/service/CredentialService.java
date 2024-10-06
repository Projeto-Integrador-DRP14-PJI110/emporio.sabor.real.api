package org.emporio.sabor.real.api.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.emporio.sabor.real.api.domain.dto.login.AuthRequestDTO;
import org.emporio.sabor.real.api.domain.dto.login.AuthResponseDTO;
import org.emporio.sabor.real.api.domain.dto.login.ChangePasswordRequestDTO;
import org.emporio.sabor.real.api.domain.dto.login.PasswordDTO;
import org.emporio.sabor.real.api.domain.model.Credential;
import org.emporio.sabor.real.api.exception.BadRequestException;
import org.emporio.sabor.real.api.exception.NotFoundException;
import org.emporio.sabor.real.api.repository.CredentialRepository;
import org.springframework.stereotype.Service;

import static org.emporio.sabor.real.api.domain.mapper.CredentialMapper.CREDENTIAL_MAPPER;

@Service
@AllArgsConstructor
@Slf4j
public class CredentialService {

    private final CredentialRepository repository;
    private final EncryptPasswordService encryptPasswordService;

    public void authenticate(String email, String password) throws Exception {
        Credential credential = validateEmailOnDatabase(email);
        if (!isPasswordValid(password, credential)) {
            throw new BadRequestException("Username or Password are not Valid");
        }
    }

    public void saveSentPassword(String email, AuthRequestDTO authRequestDTO) throws Exception {
        if (isEmailOnDatabase(email)) {
            throw new BadRequestException("Sent Email already has a registered password");
        }
        savePasswordOnDatabase(email,
                encryptPasswordService.saveSentPassword(authRequestDTO.getPassword()));
    }

    public AuthResponseDTO<PasswordDTO> generatePassword(String email) throws Exception {
        String newPassword = encryptPasswordService.generatePassword();
        savePasswordOnDatabase(email, encryptPasswordService.saveSentPassword(newPassword));
        return new AuthResponseDTO<>(new PasswordDTO(newPassword));
    }

    public void updatePassword(Credential credential, String newPassword) throws Exception {
        credential.setPassword(encryptPasswordService.saveSentPassword(newPassword));
        var credentialSaved = CREDENTIAL_MAPPER.toEntity(credential);
        repository.save(credentialSaved);
    }

    public void validateEmailOfNewPassword(String email) {
        validateEmailDoesNotExistsOnDatabase(email);
    }

    public void validateEmailDoesNotExistsOnDatabase(String email) {
        if (isEmailOnDatabase(email)) {
            throw new BadRequestException("Sent Email already has a registered password");
        }
    }

    public void savePasswordOnDatabase(String email, String password) {
        var credential = CREDENTIAL_MAPPER.toEntity(email, password);
        repository.save(credential);
    }

    public boolean isPasswordValid(String password, Credential credential) throws Exception {
        return encryptPasswordService.isPasswordValid(credential.getEmail(), password);
    }

    public Credential validateEmailOnDatabase(String email) {
        var credential = repository.findByEmail(email);
        if (credential.isEmpty()) {
            throw new NotFoundException("Email not found on Database");
        }
        return CREDENTIAL_MAPPER.toModel(credential.get());
    }

    public boolean isEmailOnDatabase(String email) {
        return repository.findByEmail(email).isPresent();
    }

    public PasswordDTO resetPassword(String email) throws Exception {
        String newPassword = encryptPasswordService.generatePassword();
        Credential credential = buildNewPasswordCredentials(email,
                encryptPasswordService.saveSentPassword(newPassword));
        savePasswordOnDatabase(credential);
        return PasswordDTO.builder().password(newPassword).build();
    }

    public void changePassword(String email, ChangePasswordRequestDTO request)
            throws Exception {
        Credential credential = validateEmailOnDatabase(email);
        if (!isChangePasswordRequestValid(credential, request.getPassword())) {
            throw new BadRequestException("Username or Password are not Valid");
        }
        updatePassword(credential, request.getNewPassword());
    }

    public boolean isChangePasswordRequestValid(Credential credential, String password)
            throws Exception {
        boolean isPasswordValid = password != null && isPasswordValid(password, credential);
        if (!isPasswordValid) {
            log.info("{} Credentials are Invalid or Not Sent", credential.getId());
        }
        return isPasswordValid;
    }

    public void savePasswordOnDatabase(Credential credential) {
        var credentialEntity = CREDENTIAL_MAPPER.toEntity(credential);
        repository.save(credentialEntity);
    }

    private Credential buildNewPasswordCredentials(String email, String newPassword) {
        Credential credential = validateEmailOnDatabase(email);
        credential.setPassword(newPassword);
        return credential;
    }
}
