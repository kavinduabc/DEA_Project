package com.opentuter.profileservice.config;

import com.opentuter.profileservice.util.Utils;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title(Utils.SWAGGER_TITLE)
                        .version(Utils.SWAGGER_VERSION)
                        .description(Utils.SWAGGER_DESCRIPTION));
    }
}
