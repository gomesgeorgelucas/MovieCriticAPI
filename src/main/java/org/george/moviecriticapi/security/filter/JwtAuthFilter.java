package org.george.moviecriticapi.security.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.george.moviecriticapi.config.JWTPropertiesConfig;
import org.george.moviecriticapi.domain.model.UserModel;
import org.george.moviecriticapi.security.service.AuthUserDetail;
import org.george.moviecriticapi.utils.APIMessages;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

@AllArgsConstructor
public class JwtAuthFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final JWTPropertiesConfig properties;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            UserModel user = new ObjectMapper().readValue(request.getInputStream(), UserModel.class);
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    user.getUserEmail(),
                    user.getUserPassword(),
                    new ArrayList<>()
            ));
        } catch (IOException | InternalAuthenticationServiceException e) {
            throw new SecurityException(APIMessages.INVALID_REQUEST_USER_NOT_AUTH_DSC);
        }
    }

    @Override
    protected void successfulAuthentication(
            HttpServletRequest request, HttpServletResponse response,
            FilterChain chain, Authentication authResult) throws IOException, ServletException {
        AuthUserDetail userDetail = (AuthUserDetail) authResult.getPrincipal();

        String token = JWT.create()
                .withSubject(userDetail.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + properties.getTokenExpirationTime()))
                .sign(Algorithm.HMAC512(properties.getTokenSecret()));

        response.getWriter().write(token);
        response.getWriter().flush();
    }
}
