package com.algaworks.algamoney_api.algamoney_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SpringDocConfing {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Algamoney API")
                        .version("v1")
                        .description("Rest API AlgaFood")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://springdoc.org"))
                        .contact(new Contact()
                                .name("AlgaWorks")
                                .url("https://www.algaworks.com")))
                        .externalDocs(new ExternalDocumentation().description("SpringDoc OpenAPI 3.0").url("https://springdoc.org/"));
    }
}
