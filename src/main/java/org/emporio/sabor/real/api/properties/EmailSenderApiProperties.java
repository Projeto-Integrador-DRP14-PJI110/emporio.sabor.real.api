package org.emporio.sabor.real.api.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "email.sender.api")
public class EmailSenderApiProperties {

    private String url;
}
