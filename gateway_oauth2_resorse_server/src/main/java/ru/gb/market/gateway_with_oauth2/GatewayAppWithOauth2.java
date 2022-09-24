package ru.gb.market.gateway_with_oauth2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;

@SpringBootApplication
@EnableWebFluxSecurity
public class GatewayAppWithOauth2 {
    public static void main(String[] args) {
        SpringApplication.run(GatewayAppWithOauth2.class, args);
    }
}
