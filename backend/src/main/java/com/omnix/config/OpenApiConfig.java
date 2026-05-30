package com.omnix.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("OMNIX API")
                        .description("Plataforma SaaS de Convites Digitais e Gestão de Eventos")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("José Jonadabe S. Barros")
                                .url("https://www.linkedin.com/in/josé-jonadabe-s-barros-2395043b7")
                        )
                )
                // Adiciona o botão "Authorize" no Swagger para testar rotas protegidas
                .addSecurityItem(new SecurityRequirement()
                        .addList("bearerAuth")
                )
                .components(new Components()
                        .addSecuritySchemes("bearerAuth",
                                new SecurityScheme()
                                        .name("bearerAuth")
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                        )
                );
    }
}