package com.example.gateway.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.List;

@Slf4j
@Component
public class AuthFilter implements GatewayFilter {

        //@Value("${jwt.secretkey}")
        private String SECRET_KEY =  "alsjflaksjfdlajdflajdlfjasdlfjladfjljfalsjflaksjfdlajdflajdlfjasdlfjladfjljfalsjflaksjfdlajdflajdlfjasdlfjladfjljf";

        @Override
        public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

            log.info(" INSIDE GATEWAY FILTER ");

            String token = extractToken(exchange);

            log.info("TOKEN : "+ token);

            if (token == null || !validateToken(token, exchange)) {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }

            return chain.filter(exchange);
        }

        private String extractToken(ServerWebExchange exchange) {
            List<String> authHeaders = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION);
            if (authHeaders == null || authHeaders.isEmpty() || !authHeaders.get(0).startsWith("Bearer ")) {
                return null;
            }
            return authHeaders.get(0).substring(7);
        }



    private boolean validateToken(String token, ServerWebExchange exchange) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY)))
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            return claims.getExpiration().after(new Date());

        } catch (Exception e) {
            return sendErrorResponse(exchange, " INVALID TOKEN", HttpStatus.UNAUTHORIZED);
        }
    }

    private boolean sendErrorResponse(ServerWebExchange exchange, String message, HttpStatus status) {
        log.error("JWT ERROR : " + message);
        exchange.getResponse().setStatusCode(status);
        return false;
    }



}

