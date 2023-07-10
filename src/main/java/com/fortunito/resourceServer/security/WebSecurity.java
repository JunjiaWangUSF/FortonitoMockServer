package com.fortunito.resourceServer.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurity {
    @Bean
    SecurityFilterChain configure(HttpSecurity http) throws Exception {

        //all request must authenticated
        http.authorizeHttpRequests(authz ->
                //verify JWT token has permission on users profile scope.
                        authz.requestMatchers(HttpMethod.GET, "/users/status/check").hasAuthority("SCOPE_profile").anyRequest().authenticated()).
        oauth2ResourceServer(oauth2 -> oauth2.
                        jwt(jwt->{}));
        //make it resource server and follow oauth2 rules and required JWT token
        return http.build();
    }
}
