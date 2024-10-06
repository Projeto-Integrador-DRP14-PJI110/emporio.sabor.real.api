package org.emporio.sabor.real.api.domain.dto.login;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PasswordMetadataDTO {

    private int metadata;
    private boolean isSaltOnLeft;
    private boolean isPepperOnLeft;
    private int stretchTimes;
    private int saltPosition;
    private int pepperPosition;

    public PasswordMetadataDTO(int metadata, boolean isSaltOnLeft, boolean isPepperOnLeft) {
        this.metadata = metadata;
        this.isSaltOnLeft = isSaltOnLeft;
        this.isPepperOnLeft = isPepperOnLeft;
        this.saltPosition = isSaltOnLeft ? 1 : 0;
        this.pepperPosition = isPepperOnLeft ? 1 : 0;
        this.stretchTimes = metadata >> 2;
    }

    public PasswordMetadataDTO(int saltPosition, int pepperPosition, int stretchTimes) {
        this.saltPosition = saltPosition;
        this.pepperPosition = pepperPosition;
        this.isSaltOnLeft = saltPosition != 0;
        this.isPepperOnLeft = pepperPosition != 0;
        this.stretchTimes = stretchTimes;
    }}
