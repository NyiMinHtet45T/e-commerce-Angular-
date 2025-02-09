package org.example.onlineshopbackend.model.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "app.token")
public class ApplicationTokenProperties {

    private Life life = new Life();
    private String issuer;

    @Data
    public static class Life {
        private int access;
        private int refresh;
    }
}
