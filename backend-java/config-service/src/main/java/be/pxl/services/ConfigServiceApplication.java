package be.pxl.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * ConfigServiceApplication
 */
@SpringBootApplication
@EnableConfigServer
@Slf4j
public class ConfigServiceApplication
{
    public static void main( String[] args )
    {
        log.debug("Config Service microservice started");
        SpringApplication.run(ConfigServiceApplication.class, args);
    }
}
