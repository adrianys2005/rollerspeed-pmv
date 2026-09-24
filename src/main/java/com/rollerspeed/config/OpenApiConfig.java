package com.rollerspeed.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API REST - Escuela de Patinaje Roller Speed")
                        .version("1.0.0")
                        .description("Documentación oficial interactiva de la API REST para la Escuela de Patinaje Roller Speed (Santa Marta, Colombia). " +
                                "Esta especificación OpenAPI 3.0 documenta los recursos de Aspirantes, Alumnos, Instructores, Clases, Pagos, Asistencia e Información Institucional " +
                                "desarrollada para la asignatura Frameworks de la IU Digital de Antioquia.")
                        .contact(new Contact()
                                .name("Equipo de Desarrollo: Adrianys Saumeth & Simón Cano Rojas")
                                .email("adrianys2005@gmail.com")
                                .url("https://github.com/adrianys2005/rollerspeed-pmv"))
                        .license(new License()
                                .name("Uso Académico - IU Digital de Antioquia")
                                .url("https://www.iudigital.edu.co")))
                .servers(List.of(
                        new Server()
                                .url("http://localhost:8080")
                                .description("Servidor Local de Desarrollo - Spring Boot Monolítico")
                ));
    }
}
