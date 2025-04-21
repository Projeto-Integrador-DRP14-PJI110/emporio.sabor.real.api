package org.emporio.sabor.real.api.connector;

import org.emporio.sabor.real.api.domain.model.RecoveryEmail;
import org.emporio.sabor.real.api.properties.EmailSenderApiProperties;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "email-sender-api", url = "${email.sender.api.url}", configuration = EmailSenderApiProperties.class)
public interface EmailSenderApiConnector {

    @PostMapping("/api/v1/recovery-mail")
    ResponseEntity<Void> sendRecoveryEmail(@RequestBody RecoveryEmail recoveryEmail);
}
