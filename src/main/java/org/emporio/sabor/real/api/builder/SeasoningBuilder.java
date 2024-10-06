package org.emporio.sabor.real.api.builder;

import jakarta.xml.bind.DatatypeConverter;
import org.emporio.sabor.real.api.domain.dto.login.PasswordMetadataDTO;

import java.security.SecureRandom;

public class SeasoningBuilder {

    public static byte[] buildSalt() {
        var salt = new byte[32];
        new SecureRandom().nextBytes(salt);
        return salt;
    }

    public static int buildSeasonPosition() {
        return new SecureRandom().nextInt(0, 2);
    }

    public static int buildStretchTimes() {
        return new SecureRandom().nextInt(20, 63);
    }

    public static byte[] extractSalt(boolean isSaltOnLeft, String password) {
        String metadataFromPassword = password.substring(0, password.length() - 2);
        if (!isSaltOnLeft) {
            return DatatypeConverter.parseHexBinary(
                    metadataFromPassword.substring(metadataFromPassword.length() - 64));
        }
        return DatatypeConverter.parseHexBinary(metadataFromPassword.substring(0, 64));
    }

    public static int seasonPassword(int seasoning, int passwordMetadata) {
        return (passwordMetadata << 1) | seasoning;
    }

    public static boolean isPepperOnLeft(int metadata) {
        int pepperMask = Integer.parseUnsignedInt("000000000000000000000000000000010", 2);
        return (metadata & pepperMask) == 2;
    }

    public static boolean isSaltOnLeft(int metadata) {
        int saltMask = Integer.parseUnsignedInt("000000000000000000000000000000001", 2);
        return (metadata & saltMask) == 1;
    }

    public static int implementSeasoning(int passwordMetadata,
                                         PasswordMetadataDTO passwordMetadataDTO) {
        return seasonPassword(passwordMetadataDTO.getSaltPosition(),
                seasonPassword(passwordMetadataDTO.getPepperPosition(), passwordMetadata));
    }

}
