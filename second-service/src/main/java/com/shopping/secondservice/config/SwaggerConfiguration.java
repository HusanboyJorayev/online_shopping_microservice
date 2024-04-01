package com.shopping.secondservice.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(info = @Info(
        description = "SECOND-SERVICE apis",
        version = "3.2.4",
        contact = @Contact(
                name = "Jorayev Husanboy",
                email = "asdf@gmail.com"
        ),
        license = @License(
                url = "http://localhost:1002/second-service/getAll",
                name = "Url for SECOND-SERVICE"
        )),
        servers = @Server(
                url = "http://localhost:1002",
                description = "SECOND-SERVICE"
        )
)
public class SwaggerConfiguration {
}
