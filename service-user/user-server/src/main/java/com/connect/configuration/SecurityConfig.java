package com.connect.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            JwtAuthenticationConverter jwtAuthenticationConverter,
            IncomingAuthDebugFilter loggingFilter
    ) throws Exception {



        http.addFilterBefore(loggingFilter,
                org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.class)

                .csrf(csrf -> csrf.disable())

                    .authorizeHttpRequests(auth -> auth

                            // Service-to-service endpoints
                            .requestMatchers("/api/v1/user/**")
                            .hasRole("INTERNAL_SERVICE")

                            .anyRequest().authenticated()
                    )


                .oauth2ResourceServer(oauth2 ->
                        oauth2.jwt(jwt ->
                                jwt.jwtAuthenticationConverter(jwtAuthenticationConverter)
                        )
                );

        return http.build();
    }
}

