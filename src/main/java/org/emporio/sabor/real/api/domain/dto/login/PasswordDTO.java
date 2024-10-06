package org.emporio.sabor.real.api.domain.dto.login;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder(toBuilder = true)
public class PasswordDTO {

    private String password;
}
