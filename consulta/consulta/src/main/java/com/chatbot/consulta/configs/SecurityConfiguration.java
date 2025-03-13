package com.chatbot.consulta.configs;

import com.chatbot.consulta.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import java.util.List;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Autowired
    SecurityFilter securityFilter;
    TokenService tokenService;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .cors(cors -> cors.configurationSource(request -> {
                    var corsConfig = new org.springframework.web.cors.CorsConfiguration();
                    corsConfig.setAllowedOrigins(List.of("http://0.0.0.0:8081")); // Domínio do front-end
                    corsConfig.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE")); // Métodos permitidos
                    corsConfig.setAllowedHeaders(List.of("*")); // Cabeçalhos permitidos
                    corsConfig.setAllowCredentials(true); // Permite envio de cookies e credenciais
                    return corsConfig;
                }))
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        //Sessao
                        .requestMatchers(HttpMethod.POST, "/sessao/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/sessao/refresh-token").permitAll()
                        .requestMatchers(HttpMethod.GET, "/sessao/get/info/usuarios").permitAll()
                        .requestMatchers(HttpMethod.GET, "/sessao/get/roles").permitAll()
                        .requestMatchers(HttpMethod.GET, "/sessao/get/info/servidores").permitAll()
                        .requestMatchers(HttpMethod.GET, "/sessao/get/info/discentes").permitAll()

                        .anyRequest().authenticated()
                )
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(securityFilter.userDetailsService());
        authProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
