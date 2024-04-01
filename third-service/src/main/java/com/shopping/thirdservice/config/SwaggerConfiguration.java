package com.shopping.thirdservice.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(info = @Info(
        description = "THIRD-SERVICE",
        version = "3.2.4",
        summary = "this is a third-service api end points",
        contact = @Contact(
                name = "Jorayev Husanboy",
                url = "https://husanboy.com",
                email = "javabackanddeveloper@gmail.com"
        ),
        license = @License(
                url = "http://localhost:1003/api/third-service",
                name = "url for THIRD-SERVICE"
        )
),
        servers = @Server(
                url = "http://localhost:1003",
                description = "THIRD-SERVICE"
        ))
public class SwaggerConfiguration {
}
