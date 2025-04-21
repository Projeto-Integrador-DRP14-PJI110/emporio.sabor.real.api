package org.emporio.sabor.real.api.service;

import lombok.AllArgsConstructor;
import org.emporio.sabor.real.api.connector.EmailSenderApiConnector;
import org.emporio.sabor.real.api.domain.model.RecoveryEmail;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailService {

    private final EmailSenderApiConnector emailSenderApiConnector;

    public void sendRecoveryEmail(RecoveryEmail recoveryEmail) {
        emailSenderApiConnector.sendRecoveryEmail(recoveryEmail);
    }
}
