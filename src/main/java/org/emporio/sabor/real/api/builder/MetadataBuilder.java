package org.emporio.sabor.real.api.builder;

import jakarta.xml.bind.DatatypeConverter;
import org.emporio.sabor.real.api.domain.dto.login.PasswordMetadataDTO;

public class MetadataBuilder {

    public static int extractMetadata(String password) throws Exception {
        byte[] metadata = DatatypeConverter.parseHexBinary(password);
        String metadataString = PasswordBuilder.bytesToHex(metadata);
        return Integer.parseInt(metadataString.substring(metadataString.length() - 2), 16);
    }

    public static int generateMetadata(PasswordMetadataDTO passwordMetadataDTO) {
        int passwordMetadata = adjustInitialMetadataToMask(passwordMetadataDTO.getStretchTimes());
        return SeasoningBuilder.implementSeasoning(passwordMetadata, passwordMetadataDTO);
    }

    public static int adjustInitialMetadataToMask(int stretchTimes) {
        int metadataMask = Integer.parseUnsignedInt("00000000000000000000000011111111", 2);
        return stretchTimes & metadataMask;
    }


}
