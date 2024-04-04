package com.apigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

	@Bean
	public SecurityWebFilterChain filterChain(ServerHttpSecurity httpSecurity) {
		httpSecurity
        .authorizeExchange(exchanges ->
                exchanges.anyExchange().authenticated()
        )
        .oauth2Login()
        .and()
        .oauth2ResourceServer()
        .jwt();
return httpSecurity.build();
	}
	
}
