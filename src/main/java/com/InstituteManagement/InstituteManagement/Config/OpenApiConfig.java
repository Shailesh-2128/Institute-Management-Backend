package com.InstituteManagement.InstituteManagement.Config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI instituteCRMOpenAPI() {

        return new OpenAPI()
                .info(new Info()
                        .title("Institute CRM API")
                        .description("Institute CRM Backend APIs")
                        .version("1.0")

                        .contact(new Contact()
                                .name("Shailesh")
                                .email("support@crm.com"))

                        .license(new License()
                                .name("API License")
                                .url("https://opensource.org/licenses/MIT")));
    }
}