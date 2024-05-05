package com.example.jwt.configuration;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer",
        in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {
    @Bean
    public OpenAPI openAPIInfo() {
        return new OpenAPI().info(
                new Info()
                        .title("JWT Template API")
                        .description("Documenting JWT Template with SpringDoc and OpenAPI")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("ardiansah")
                                .email("ardiansah.info@gmail.com")));
    }

}
