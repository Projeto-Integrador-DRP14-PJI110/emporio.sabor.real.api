package org.emporio.sabor.real.api.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecoveryEmailDTO {

    private String email;
    private String token;
    private String subject;
}
