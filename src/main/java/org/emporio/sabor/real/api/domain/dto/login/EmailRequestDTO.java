package org.emporio.sabor.real.api.domain.dto.login;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EmailRequestDTO {

    @NotNull
    private String email;
}
