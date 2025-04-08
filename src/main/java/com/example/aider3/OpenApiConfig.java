package com.example.aider3;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI aider3OpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Aider3 API")
                        .description("API documentation for Aider3 application")
                        .version("v1.0")
                        .contact(new Contact().name("Support").email("support@example.com"))
                        .license(new License().name("Apache 2.0").url("https://springdoc.org")));
    }
}
