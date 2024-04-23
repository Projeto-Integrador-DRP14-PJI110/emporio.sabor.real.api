package org.emporio.sabor.real.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "org.emporio")
@EnableJpaRepositories(basePackages = "org.emporio.sabor.real.api.repository")
public class EmporioSaborRealApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmporioSaborRealApplication.class, args);
    }

}
