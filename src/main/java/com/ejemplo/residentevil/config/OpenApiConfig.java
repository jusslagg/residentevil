package com.ejemplo.residentevil.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI springResidentevilOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("API Resident Evil")
                        .description("Modelo MVC para la API de Resident Evil.")
                        .version("v1.0.0")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("Documentación de Resident Evil API")
                        .url("https://residentevilapi.docs.github.org"));
    }
}
