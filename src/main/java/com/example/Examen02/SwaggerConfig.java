package com.example.Examen02;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI configuration() {
        return new OpenAPI()
                .info(new Info()
                        .title("Exam02")
                        .description("Exam02")
                        .version("2.1.6")
                        .contact(new Contact().name("Amir")));
    }

}