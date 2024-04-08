package org.emporio.sabor.real.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "org.emporio")
public class EmporioSaborRealApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmporioSaborRealApplication.class, args);
    }

}
