package com.restaurant.searcher.infrastructure.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(title = "API Restaurant search",
        description = "Documentation",
                contact = @Contact(name = "Antonio Lazaro Carvalho Borges", email = "antonio.lazaro@gmail.com")),
        servers = {
                @Server(url = "http://localhost:8080/"),
                @Server(url = "http://heroku/")
        }
)
public class SwaggerConfig {

}
