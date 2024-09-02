package com.gifter.app.security.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
    private static final String AUTHENTICATION_HEADER = "Authentication";
    private static final String TOKEN_NAME = "Bearer";

    @Autowired
    private final JwtService jwtService;
    @Autowired
    private final UserDetailsService userDetailsService;

    // we can intercept each request and extract data
    // and also provide new data on the request
    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain filterChain) throws ServletException, IOException {
        final String desiredStart = TOKEN_NAME + " ";
        final String authHeader = request.getHeader(AUTHENTICATION_HEADER);

        if (authHeader == null || !authHeader.startsWith(desiredStart)) {
            filterChain.doFilter(request, response);
            return;
        }
        final String jwtToken = authHeader.substring(desiredStart.length());
        final String email = jwtService.extractUsername(jwtToken);
        final boolean alreadyAuthenticated = SecurityContextHolder.getContext().getAuthentication() != null;
        if (email != null && !alreadyAuthenticated) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(email);
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
