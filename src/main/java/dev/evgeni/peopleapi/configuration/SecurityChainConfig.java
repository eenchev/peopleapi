package dev.evgeni.peopleapi.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import dev.evgeni.peopleapi.web.JWTLoginFilter;

@Configuration
public class SecurityChainConfig {

    @Autowired
    private JWTLoginFilter jwtLoginFilter;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.authorizeHttpRequests(auth -> {
            auth.requestMatchers(HttpMethod.GET, "/dummy").permitAll();
            auth.requestMatchers(HttpMethod.GET, "/photo").permitAll();
            auth.requestMatchers(HttpMethod.GET, "/person").permitAll();
            auth.requestMatchers(HttpMethod.POST, "/person").hasRole("ADMIN");
            auth.requestMatchers(HttpMethod.POST, "/token").permitAll();
            auth.requestMatchers(HttpMethod.GET, "/senddevstatistics").permitAll();
            auth.requestMatchers(HttpMethod.GET, "/swagger-ui.html", "/swagger-ui/**", "/v3/**")
                    .permitAll();
        }).csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(jwtLoginFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(
                        (session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

}
