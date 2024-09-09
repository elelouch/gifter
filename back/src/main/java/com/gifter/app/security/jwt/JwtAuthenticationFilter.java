package com.gifter.app.security.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNullApi;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private static final String AUTHENTICATION_HEADER = "Authorization";
    private static final String TOKEN_NAME = "Bearer ";

    @Autowired
    private final JwtService jwtService;
    @Autowired
    private final UserDetailsService userDetailsService;

    // we can intercept each request from the filter chain and extract data
    // and also provide new data on the request
    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader(AUTHENTICATION_HEADER);

        if (authHeader == null || !authHeader.startsWith(TOKEN_NAME)) {
            filterChain.doFilter(request, response);
            return;
        }
        final String jwtToken = authHeader.substring(TOKEN_NAME.length());

        final String stringId = jwtService.extractId(jwtToken);
        final boolean alreadyAuthenticated = SecurityContextHolder.getContext().getAuthentication() != null;
        if (stringId != null && !alreadyAuthenticated) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(stringId);
            if (jwtService.isTokenValid(jwtToken, userDetails)) {
                // required for updating security context, we don't have credentials yet. That's why we're passing null
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                // update security context holder
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
            filterChain.doFilter(request, response);
        }
    }
}
