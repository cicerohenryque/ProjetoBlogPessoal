package com.blogpessoal.config;

import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.*;
import io.swagger.v3.oas.annotations.security.*;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Blog Pessoal",
                version = "v1.0",
                description = "API do Projeto Blog Pessoal",
                contact = @Contact(
                        name = "Cicero Henryque",
                        email = "cicerohenryque@gmail.com"
                )
        ),
        servers = @Server(url = "http://localhost:8080")
)
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class SwaggerConfig {
}
