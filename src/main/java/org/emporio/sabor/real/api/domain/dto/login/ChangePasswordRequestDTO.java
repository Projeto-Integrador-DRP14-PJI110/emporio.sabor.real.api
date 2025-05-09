package org.emporio.sabor.real.api.domain.dto.login;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ChangePasswordRequestDTO {

    @NotNull
    private String email;
    @NotNull
    private String password;
    @NotNull
    private String newPassword;
}