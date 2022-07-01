package org.george.moviecriticapi.security.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import org.george.moviecriticapi.config.JWTPropertiesConfig;
import org.george.moviecriticapi.utils.APIMessages;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class JwtCheckFilter extends BasicAuthenticationFilter {
    private final JWTPropertiesConfig properties;

    public JwtCheckFilter(AuthenticationManager authenticationManager, JWTPropertiesConfig properties) {
        super(authenticationManager);
        this.properties = properties;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        String HEADER_ATTRIBUTE = "token";

        String token = request.getParameter(HEADER_ATTRIBUTE);

        if (token == null) {
            chain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken authenticationToken = this.getAuthenticationToken(token);

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthenticationToken(String token) {
        try {
            String userEmail = JWT.require(Algorithm.HMAC512(properties.getTokenSecret()))
                    .build()
                    .verify(token)
                    .getSubject();

            return (userEmail == null) ? null : new UsernamePasswordAuthenticationToken(userEmail, null, new ArrayList<>());
        } catch (SignatureVerificationException e) {
            throw new SecurityException(APIMessages.INVALID_JWT_TOKEN_DSC);
        }
    }
}
