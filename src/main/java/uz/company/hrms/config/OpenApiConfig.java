package uz.company.hrms.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "HRMS API",
                version = "1.0.0",
                description = "REST API for employees"
        )
)
public class OpenApiConfig {
}
