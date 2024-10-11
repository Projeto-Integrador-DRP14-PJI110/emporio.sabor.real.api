package org.emporio.sabor.real.api.controller;

import io.micrometer.common.lang.Nullable;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;

import org.emporio.sabor.real.api.domain.dto.login.AuthRequestDTO;
import org.emporio.sabor.real.api.domain.dto.login.AuthResponseDTO;
import org.emporio.sabor.real.api.domain.dto.login.ChangePasswordRequestDTO;
import org.emporio.sabor.real.api.domain.dto.login.PasswordDTO;
import org.emporio.sabor.real.api.service.CredentialService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/credential")
@AllArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Tag(name = "Login", description = "Login API")
public class LoginController {

    private final CredentialService credentialService;

    @PostMapping(value = "/login/{email}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Realiza login através do email informado", description = "Realiza login através do email informado", tags = {
            "Login"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Realiza login através do email informado")})
    public ResponseEntity<AuthResponseDTO<Void>> login(
            @PathVariable("email") @NotNull String email, @RequestBody AuthRequestDTO authRequestDTO)
            throws Exception {
        credentialService.authenticate(email.toLowerCase(), authRequestDTO.getPassword());
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/{email}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Realiza autenticação através do email informado", description = "Realiza autenticação através do email informado", tags = {
            "Credential"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Realiza autenticação através do email informado")})
    public ResponseEntity<AuthResponseDTO<Void>> authenticate(
            @PathVariable("email") @NotNull String email,
            @RequestBody @NotNull AuthRequestDTO authRequestDTO) throws Exception {
        credentialService.validateEmailOfNewPassword(email.toLowerCase());
        credentialService.saveSentPassword(email.toLowerCase(), authRequestDTO);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{email}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Realiza mudança de senha através do email informado", description = "Realiza mudança de senha através do email informado", tags = {
            "Credential"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Realiza mudança de senha através do email informado")})
    public ResponseEntity<AuthResponseDTO<Void>> changePassword(
            @PathVariable("email") @NotNull String email,
            @RequestBody @Nullable ChangePasswordRequestDTO request)
            throws Exception {
        credentialService.changePassword(email.toLowerCase(), request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping(value = "/{email}/reset", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Reseta um e-mail existente", description = "Reseta um e-mail existente", tags = {
            "Credential"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reseta um e-mail existente")})
    public ResponseEntity<AuthResponseDTO<PasswordDTO>> reset(
            @PathVariable("email") @NotNull String email) throws Exception {
        var response = credentialService.resetPassword(email.toLowerCase());
        return ResponseEntity.ok(new AuthResponseDTO<>(response));
    }
}