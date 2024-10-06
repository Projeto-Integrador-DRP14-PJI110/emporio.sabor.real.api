package org.emporio.sabor.real.api.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "spring.auth", ignoreUnknownFields = false)
public class AuthProperties {

    private String apiKey;
    private String key;
    private String pepper;}
