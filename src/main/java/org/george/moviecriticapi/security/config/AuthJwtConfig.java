package org.george.moviecriticapi.security.config;

import lombok.AllArgsConstructor;
import org.george.moviecriticapi.config.JWTPropertiesConfig;
import org.george.moviecriticapi.security.filter.JwtAuthFilter;
import org.george.moviecriticapi.security.service.AuthUserService;
import org.george.moviecriticapi.security.service.JwtCheckFilter;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@AllArgsConstructor
public class AuthJwtConfig extends WebSecurityConfigurerAdapter {
    private final AuthUserService userService;
    private final PasswordEncoder encoder;
    private final JWTPropertiesConfig properties;

    @Override
    protected void configure(AuthenticationManagerBuilder amb) throws Exception {
        amb.userDetailsService(userService).passwordEncoder(encoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .antMatchers(HttpMethod.POST, "/signUp").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(new JwtAuthFilter(authenticationManager(), properties))
                .addFilter(new JwtCheckFilter(authenticationManager(), properties))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}
