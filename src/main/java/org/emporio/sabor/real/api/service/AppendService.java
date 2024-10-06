package org.emporio.sabor.real.api.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.emporio.sabor.real.api.domain.dto.login.PasswordMetadataDTO;
import org.emporio.sabor.real.api.domain.dto.login.PasswordSeasoningDTO;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
@Slf4j
@AllArgsConstructor
public class AppendService {

    public byte[] appendSalt(PasswordSeasoningDTO passwordSeasoningDTO, PasswordMetadataDTO pm)
            throws IOException {
        if (pm.isSaltOnLeft()) {
            return appendSeasoning(passwordSeasoningDTO.getSalt(),
                    passwordSeasoningDTO.getPassword());
        }
        return appendSeasoning(passwordSeasoningDTO.getPassword(), passwordSeasoningDTO.getSalt());
    }

    public byte[] appendPepper(PasswordSeasoningDTO passwordSeasoningDTO, PasswordMetadataDTO pm)
            throws IOException {
        if (pm.isPepperOnLeft()) {
            return appendSeasoning(passwordSeasoningDTO.getPepper(),
                    passwordSeasoningDTO.getPassword());
        }
        return appendSeasoning(passwordSeasoningDTO.getPassword(),
                passwordSeasoningDTO.getPepper());
    }

    public byte[] appendMetadata(byte[] password, int metadata) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bos.write(password);
        bos.write(metadata);
        return bos.toByteArray();
    }

    private byte[] appendSeasoning(byte[] a, byte[] b) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bos.write(a);
        bos.write(b);
        return bos.toByteArray();
    }
}
