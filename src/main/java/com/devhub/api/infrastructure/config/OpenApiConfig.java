package com.devhub.api.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .addServersItem(new io.swagger.v3.oas.models.servers.Server()
                        .url("https://dev-hub-backend-frmz.onrender.com")
                        .description("Servidor de Produção"))
                .addServersItem(new io.swagger.v3.oas.models.servers.Server()
                        .url("http://localhost:8081")
                        .description("Servidor Local"))
                .info(new io.swagger.v3.oas.models.info.Info()
                        .title("DevResources Hub API")
                        .version("1.0"));
    }
}
