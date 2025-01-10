package be.pxl.services;

import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;

import java.util.ArrayList;
import java.util.List;

/**
 * GatewayServiceApplication
 */
@SpringBootApplication
@EnableDiscoveryClient
@Slf4j
public class GatewayServiceApplication
{
    public static void main( String[] args )
    {
        log.debug("Gateway Service microservice started");
        SpringApplication.run(GatewayServiceApplication.class, args);
    }

    @Bean
    @Lazy(false)
    public List<GroupedOpenApi> apis(RouteDefinitionLocator locator) {
        List<GroupedOpenApi> groups = new ArrayList<>();
        List<RouteDefinition> definitions = locator.getRouteDefinitions().collectList().block();
        if (definitions != null) {
            definitions.stream().filter(routeDefinition -> routeDefinition.getId().matches(".*-service")).forEach(routeDefinition -> {
                String name = routeDefinition.getId().replaceAll("-service", "");
                GroupedOpenApi.builder().pathsToMatch("/" + name + "/**").group(name).build();
            });
        }
        return groups;
    }
}
