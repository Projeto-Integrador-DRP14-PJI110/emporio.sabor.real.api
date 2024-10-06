package org.emporio.sabor.real.api.service;

import jakarta.xml.bind.DatatypeConverter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.emporio.sabor.real.api.builder.PasswordBuilder;
import org.emporio.sabor.real.api.builder.SeasoningBuilder;
import org.emporio.sabor.real.api.domain.dto.login.PasswordMetadataDTO;
import org.emporio.sabor.real.api.domain.dto.login.PasswordSeasoningDTO;
import org.emporio.sabor.real.api.properties.AuthProperties;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
@Slf4j
@AllArgsConstructor
public class EncryptPasswordService {

    private final AuthProperties authProperties;
    private final AppendService appendService;

    public String generatePassword() {
        return PasswordBuilder.bytesToHex(PasswordBuilder.buildNewPassword());
    }

    public String saveSentPassword(String password) throws Exception {
        return encryptPassword(PasswordBuilder.buildSentPassword(password));
    }

    public String encryptPassword(byte[] password) throws Exception {
        String seasonedPassword = saltedHashing(PasswordBuilder.getNewPasswordMetadata(),
                new PasswordSeasoningDTO(password,
                        DatatypeConverter.parseHexBinary(authProperties.getPepper())));
        return PasswordBuilder.bytesToHex(encrypt(seasonedPassword));
    }

    public boolean isPasswordValid(String existingPassword, String sentPassword) throws Exception {
        return seasonSentPassword(decrypt(existingPassword), sentPassword).equals(
                decrypt(existingPassword));
    }

    private String seasonSentPassword(String realPassword, String sentPassword) throws Exception {
        PasswordMetadataDTO passwordMetadataDTO = PasswordBuilder.getPasswordMetadata(realPassword);
        byte[] salt = SeasoningBuilder.extractSalt(passwordMetadataDTO.isSaltOnLeft(),
                realPassword);
        return saltedHashing(passwordMetadataDTO, new PasswordSeasoningDTO(salt,
                DatatypeConverter.parseHexBinary(authProperties.getPepper()),
                sentPassword.getBytes()));
    }

    private String saltedHashing(PasswordMetadataDTO pm, PasswordSeasoningDTO passwordSeasoningDTO)
            throws Exception {
        for (int i = 0; i < pm.getStretchTimes(); i++) {
            passwordSeasoningDTO.setPassword(appendSeasonPassword(passwordSeasoningDTO, pm));
        }
        passwordSeasoningDTO.setPassword(appendService.appendSalt(passwordSeasoningDTO, pm));
        passwordSeasoningDTO.setPassword(
                appendService.appendMetadata(passwordSeasoningDTO.getPassword(), pm.getMetadata()));
        return PasswordBuilder.bytesToHex(passwordSeasoningDTO.getPassword());
    }

    private byte[] encrypt(String password)
            throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, getKeySpec());
        return cipher.doFinal(password.getBytes());
    }

    public String decrypt(String password)
            throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, getKeySpec());
        byte[] passwordDecrypted = cipher.doFinal(DatatypeConverter.parseHexBinary(password));
        return new String(passwordDecrypted);
    }

    private SecretKeySpec getKeySpec() {
        return new SecretKeySpec(authProperties.getKey().getBytes(), "AES");
    }

    private byte[] appendSeasonPassword(PasswordSeasoningDTO passwordSeasoning,
                                        PasswordMetadataDTO pm) throws IOException, NoSuchAlgorithmException {
        passwordSeasoning.setPassword(appendService.appendPepper(passwordSeasoning, pm));
        passwordSeasoning.setPassword(appendService.appendSalt(passwordSeasoning, pm));
        passwordSeasoning.setPassword(
                MessageDigest.getInstance("SHA3-512").digest(passwordSeasoning.getPassword()));
        return passwordSeasoning.getPassword();
    }
}
