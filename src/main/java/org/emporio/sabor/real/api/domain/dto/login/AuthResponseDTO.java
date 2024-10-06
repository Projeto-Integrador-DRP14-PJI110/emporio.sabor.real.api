package org.emporio.sabor.real.api.domain.dto.login;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.emporio.sabor.real.api.filter.ControllerAdvisor;

import java.util.List;

@Data
@AllArgsConstructor
public class AuthResponseDTO<T> {

    private T response;
    private List<ControllerAdvisor.ErrorDTO> errors;

    public AuthResponseDTO(T message) {
        this.response = message;
    }
}
