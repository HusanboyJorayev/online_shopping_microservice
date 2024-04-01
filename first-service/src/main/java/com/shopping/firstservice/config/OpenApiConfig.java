package com.shopping.firstservice.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(info = @Info(
        description = "This is a FIRST-SERVICE end points",
        version = "3.2.4",
        summary = "this is a FIRST-SERVICE application",
        license = @License(
                name = "FIRST-SERVICE",
                url = "http://localhost:1001/api/first-service/customers"
        ),
        contact = @Contact(
                email = "husanboybackanddeveloper@gmail.com",
                name = "Husanboy Jorayev"
        )

),
        servers = @Server(
                url = "http://localhost:1001",
                description = "FIRST-SERVICE"
        )
)
public class OpenApiConfig {
}
