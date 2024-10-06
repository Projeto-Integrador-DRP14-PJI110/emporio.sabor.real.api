package org.emporio.sabor.real.api.domain.dto.login;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ChangePasswordRequestDTO {

    private String password;
    private String newPassword;
}