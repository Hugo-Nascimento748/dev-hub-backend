package com.devhub.api.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("DevResources Hub API")
                        .version("1.0")
                        .description("Plataforma de curadoria de links e recursos para desenvolvedores.")
                        .contact(new Contact()
                                .name("Hugo Nascimento")
                                .email("nascimentohugo748@gmail.com")));

    }
}
