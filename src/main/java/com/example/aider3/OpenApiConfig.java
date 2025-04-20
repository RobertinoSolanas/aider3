package com.example.aider3;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Value("${springdoc.server-url}")
    private String serverUrl;

    @Bean
    public OpenAPI checkersOpenAPI() {
        return new OpenAPI()
                .servers(List.of(new Server().url(serverUrl)))
                .info(new Info().title("Checkers Game API")
                        .description("API documentation for Checkers Game Service")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("API Support")
                                .email("support@checkers.example.com")
                                .url("https://checkers.example.com/contact"))
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT")))
                .externalDocs(new io.swagger.v3.oas.models.ExternalDocumentation()
                        .description("Checkers Game Rules")
                        .url("https://en.wikipedia.org/wiki/Checkers"));
    }
}
