package com.fortunito.resourceServer.security;

import org.springframework.http.HttpMethod;

import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true) //allow to use secure annotations
// manage method level access for further used.
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter{
    @Override
    /**
     * Configure class to make rules for any http request
     * @param http Any Http request
     * @throws Exception 403 forbidden if any role or scope not matched in following rules.
     */
    protected void configure(HttpSecurity http) throws Exception {
        //JWT parser classe to get role of current user.
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new KeycloakRoleConverter());
        //user must be authorized and any get request in /users/status/check must have developer role
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/users/status/check")
                //.hasAuthority("SCOPE_profile")
                .hasRole("developer")
                //.hasAnyRole("devleoper","user")
                .anyRequest().authenticated()
                .and()
                .oauth2ResourceServer()
                .jwt()
                .jwtAuthenticationConverter(jwtAuthenticationConverter);
        //passed in the role.
    }

}
