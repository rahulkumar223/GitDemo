package com.swagger.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;


@Configuration
public class SwaggerConfig {


    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI().info(
                        new Info()
                                .title("Swagger-Spring boot project Rest Apis")
                                .version("1.0.0")
                                .description("API documentation for learning swagger")
                                .contact(new Contact().name("DesiQNA")
                                        .email("desiqna@gmail.com")
                                        .url("------"))
                                .license(new License()
                                        .name("Apache 2.0")
                                        .url("-------"))
                )
                .servers(List.of(new Server().url("https://api.prod.example.com")
                                        .description("Production server")
                                , new Server().url("https://api.int.example.com")
                                        .description("integration server"),
                                new Server().url("https://api.dev.example.com")
                                        .description("Development server")
                                , new Server().url("http:localhost:8080")
                                        .description("Local server")
                        )

                );


    }
}
