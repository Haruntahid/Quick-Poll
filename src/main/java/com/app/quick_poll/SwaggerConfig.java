package com.app.quick_poll;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
        @Bean
    public OpenAPI customAPI(){
           return new OpenAPI().info(
            new Info().title("Quick Poll").version("1.0")
                   .description("Quick Poll API")
           );
        }


}
