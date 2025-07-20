package com.javafest.interviewmentor.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {
    
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Interview Mentor API")
                        .description("API documentation for Interview Mentor platform's chat functionality")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Interview Mentor Team")
                                .email("contact@interviewmentor.com")));
    }
} 