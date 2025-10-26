package com.max.carpincho.security.config.filter;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.max.carpincho.security.service.util.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

public class JwtTokenValidator extends OncePerRequestFilter {

    private JwtUtils jwtUtils;

    public JwtTokenValidator(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        String jwtToken = request.getHeader(HttpHeaders.AUTHORIZATION);

        if(jwtToken != null) {
            jwtToken = jwtToken.substring(7);

            DecodedJWT decodedJWT = jwtUtils.validateToken(jwtToken);

            String username = jwtUtils.extractUsername(decodedJWT);
            String stringAuthorities = jwtUtils.getEspecificClaim(decodedJWT, "authorities").asString();

            List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(
                    StringUtils.tokenizeToStringArray(stringAuthorities, ", ")
            );

            SecurityContext context = SecurityContextHolder.getContext();
            context.setAuthentication(new UsernamePasswordAuthenticationToken(username, null, authorities));
            SecurityContextHolder.setContext(context);
        }
        filterChain.doFilter(request, response);
    }
}




