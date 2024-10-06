package org.emporio.sabor.real.api.builder;

import org.emporio.sabor.real.api.domain.dto.login.PasswordMetadataDTO;

import java.security.SecureRandom;

public class PasswordBuilder {

    public static byte[] buildNewPassword() {
        byte[] password = new byte[32];
        new SecureRandom().nextBytes(password);
        return password;
    }

    public static byte[] buildSentPassword(String password) {
        return password.getBytes();
    }

    public static PasswordMetadataDTO getNewPasswordMetadata() {
        PasswordMetadataDTO passwordMetadataDTO = getSeasonMetadata();
        passwordMetadataDTO.setMetadata(MetadataBuilder.generateMetadata(passwordMetadataDTO));
        return passwordMetadataDTO;
    }

    public static PasswordMetadataDTO getPasswordMetadata(String password) throws Exception {
        int metadata = MetadataBuilder.extractMetadata(password);
        return new PasswordMetadataDTO(metadata, SeasoningBuilder.isSaltOnLeft(metadata),
                SeasoningBuilder.isPepperOnLeft(metadata));
    }

    private static PasswordMetadataDTO getSeasonMetadata() {
        return new PasswordMetadataDTO(SeasoningBuilder.buildSeasonPosition(),
                SeasoningBuilder.buildSeasonPosition(), SeasoningBuilder.buildStretchTimes());
    }

    public static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (byte singleByte : hash) {
            hexString = byteToHex(singleByte, hexString);
        }
        return hexString.toString();
    }

    private static StringBuilder byteToHex(byte singleByte, StringBuilder hexString) {
        String hex = Integer.toHexString(0xff & singleByte);
        if (hex.length() == 1) {
            hexString.append('0');
        }
        return hexString.append(hex);
    }
}
