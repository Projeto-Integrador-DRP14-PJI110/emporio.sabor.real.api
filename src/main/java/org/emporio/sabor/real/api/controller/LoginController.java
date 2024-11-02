package org.emporio.sabor.real.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.emporio.sabor.real.api.domain.dto.login.AuthRequestDTO;
import org.emporio.sabor.real.api.domain.dto.login.AuthResponseDTO;
import org.emporio.sabor.real.api.service.CredentialService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/v1/login", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Validated
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Tag(name = "Login", description = "Login API")
public class LoginController {

    private final CredentialService credentialService;

    @PostMapping
    @Operation(summary = "Realiza login através do email informado", description = "Realiza login através do email informado", tags = {
            "Login"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Realiza login através do email informado")})
    public ResponseEntity<AuthResponseDTO<Void>> login(@Valid @RequestBody AuthRequestDTO authRequestDTO)
            throws Exception {
        credentialService.authenticate(authRequestDTO.getEmail().toLowerCase(), authRequestDTO.getPassword());
        return ResponseEntity.ok().build();
    }
}