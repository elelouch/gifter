package com.gifter.app.security.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private static final String AUTHENTICATION_HEADER = "Authentication";
    private static final String TOKEN_NAME = "Bearer";

    private final JwtService jwtService;
    // we can intercept each request and extract data
    // and also provide new data on the request
    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response,@NotNull FilterChain filterChain) throws ServletException, IOException {
        final String desiredStart = TOKEN_NAME + " ";
        final String authHeader = request.getHeader(AUTHENTICATION_HEADER);

        if(authHeader == null || !authHeader.startsWith(desiredStart)) {
            filterChain.doFilter(request,response);
            return;
        }
        final String jwtToken = authHeader.substring(desiredStart.length());
        final String email = jwtService.extractUsername(jwtToken);
    }
}
