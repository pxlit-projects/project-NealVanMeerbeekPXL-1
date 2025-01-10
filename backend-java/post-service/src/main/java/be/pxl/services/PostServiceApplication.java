package be.pxl.services;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * PostServiceApplication
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@Slf4j
@OpenAPIDefinition(info = @Info(title = "Post API", version = "V${api.version}", description = "Documentation Post API V${api.version}"))
public class PostServiceApplication
{
    public static void main( String[] args )
    {
        log.debug("Post Service microservice started");
        SpringApplication.run(PostServiceApplication.class, args);
    }
}
