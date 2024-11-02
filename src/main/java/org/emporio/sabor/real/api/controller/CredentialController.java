package org.emporio.sabor.real.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.emporio.sabor.real.api.domain.dto.login.*;
import org.emporio.sabor.real.api.service.CredentialService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/v1/credential", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Tag(name = "Credential", description = "Credential API")
public class CredentialController {

    private final CredentialService credentialService;

    @PostMapping
    @Operation(summary = "Realiza autenticação através do email informado", description = "Realiza autenticação através do email informado", tags = {
            "Credential"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Realiza autenticação através do email informado")})
    public ResponseEntity<AuthResponseDTO<Void>> authenticate(
            @Valid @RequestBody AuthRequestDTO request) throws Exception {
        credentialService.validateEmailOfNewPassword(request.getEmail().toLowerCase());
        credentialService.saveSentPassword(request.getEmail().toLowerCase(), request);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    @Operation(summary = "Realiza mudança de senha através do email informado", description = "Realiza mudança de senha através do email informado", tags = {
            "Credential"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Realiza mudança de senha através do email informado")})
    public ResponseEntity<AuthResponseDTO<Void>> changePassword(
            @Valid @RequestBody ChangePasswordRequestDTO request)
            throws Exception {
        credentialService.changePassword(request.getEmail().toLowerCase(), request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping(value = "/reset", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Reseta um e-mail existente", description = "Reseta um e-mail existente", tags = {
            "Credential"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reseta um e-mail existente")})
    public ResponseEntity<AuthResponseDTO<PasswordDTO>> reset(
            @RequestBody EmailRequestDTO request) throws Exception {
        var response = credentialService.resetPassword(request.getEmail().toLowerCase());
        return ResponseEntity.ok(new AuthResponseDTO<>(response));
    }
}
