package org.george.moviecriticapi;

import org.george.moviecriticapi.config.JWTPropertiesConfig;
import org.george.moviecriticapi.config.OMDBPropertiesConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableConfigurationProperties({OMDBPropertiesConfig.class, JWTPropertiesConfig.class})
public class MovieCriticApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovieCriticApiApplication.class, args);
    }
}
