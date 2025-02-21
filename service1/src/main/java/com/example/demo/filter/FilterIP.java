package com.example.demo.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
public class FilterIP extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        log.info("VERIFYING IP ADDRESS");

        String header = request.getHeader("gateway-header");

        log.info("RESOURCE REQUESTED BY : " + header);

        if (header == null || !header.equals("alpha")) {
            log.warn("UNAUTHORISED ACCESS " + header);
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "ACCCESS DENIED");
            return;
        }

        filterChain.doFilter(request, response);
    }
}
