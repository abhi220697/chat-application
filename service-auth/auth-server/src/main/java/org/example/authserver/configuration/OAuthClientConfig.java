package org.example.authserver.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;

import java.util.UUID;

@Configuration
public class OAuthClientConfig {

    @Bean
    public RegisteredClientRepository registeredClientRepository() {

        RegisteredClient userServiceClient =
                RegisteredClient.withId(UUID.randomUUID().toString())
                        .clientId("user-service")
                        .clientSecret("{noop}user-secret") // dev only
                        .clientAuthenticationMethod(
                                ClientAuthenticationMethod.CLIENT_SECRET_BASIC
                        )
                        .authorizationGrantType(
                                AuthorizationGrantType.CLIENT_CREDENTIALS
                        )
                        .scope("internal")
                        .build();

        return new InMemoryRegisteredClientRepository(userServiceClient);
    }
}
