package com.example.gateway.config;

import com.example.gateway.filter.AuthFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()

                .route("ADDRESS-SERVICE", r -> r.path("/api/v1/address/**")
                        .filters(f -> f.filter(new AuthFilter()))
                        .uri("http://127.0.0.1:8081"))

                .route("USER-SERVICE", r -> r.path("/api/v1/user/login")
                        .uri("http://127.0.0.1:8082"))

                .route("USER-SERVICE", r -> r.path("/api/v1/user/")
                        .uri("http://127.0.0.1:8082"))

                .route("USER-SERVICE", r -> r.path("/api/v1/user/**")
                        .filters(f -> f.filter(new AuthFilter()))
                        .uri("http://127.0.0.1:8082"))

                .build();
    }
}
