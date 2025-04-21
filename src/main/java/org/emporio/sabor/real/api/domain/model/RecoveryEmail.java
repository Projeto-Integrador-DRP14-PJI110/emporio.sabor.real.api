package org.emporio.sabor.real.api.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecoveryEmail {

    private String email;
    private String token;
    private String subject;
}
