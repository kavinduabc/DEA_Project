package com.opentuter.enrollmentservice.config;

import com.opentuter.enrollmentservice.util.EnrollmentUtil;
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
                        .title(EnrollmentUtil.SWAGGER_TITLE)
                        .version(EnrollmentUtil.SWAGGER_VERSION)
                        .description(EnrollmentUtil.SWAGGER_DESCRIPTION));
    }
}
