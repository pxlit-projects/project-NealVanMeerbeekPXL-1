package be.pxl.services;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * CommentServiceApplication
 */
@SpringBootApplication
@EnableDiscoveryClient
@Slf4j
@OpenAPIDefinition(info = @Info(title = "Comment API", version = "V${api.version}", description = "Documentation Comment API V${api.version}"))
public class CommentServiceApplication
{
    public static void main( String[] args )
    {
        log.debug("Comment Service microservice started");
        SpringApplication.run(CommentServiceApplication.class, args);
    }
}
