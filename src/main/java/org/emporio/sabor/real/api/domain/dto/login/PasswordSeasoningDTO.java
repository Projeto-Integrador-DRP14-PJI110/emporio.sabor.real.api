package org.emporio.sabor.real.api.domain.dto.login;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.emporio.sabor.real.api.builder.SeasoningBuilder;

@Data
@AllArgsConstructor
public class PasswordSeasoningDTO {

    private byte[] salt;
    private byte[] pepper;
    private byte[] password;

    public PasswordSeasoningDTO(byte[] password, byte[] pepper) {
        this.password = password;
        this.pepper = pepper;
        this.salt = SeasoningBuilder.buildSalt();
    }
}
