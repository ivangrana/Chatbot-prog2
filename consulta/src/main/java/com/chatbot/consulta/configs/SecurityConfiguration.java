package com.chatbot.consulta.configs;

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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Autowired
    SecurityFilter securityFilter;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/v2/api-docs",
                                "/v3/api-docs/**",
                                "/v3/api-docs",
                                "/swagger-ui/**",
                                "/swagger-resources/**",
                                "/swagger-resources",
                                "/configuration/ui",
                                "/configuration/security",
                                "/webjars/**",
                                "/swagger-ui.html").permitAll()
                        .requestMatchers(HttpMethod.POST, "/autenticacao/helloWorld").permitAll()
                        .requestMatchers(HttpMethod.POST, "/autenticacao/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/autenticacao/paciente").hasAnyRole("ROLE_MEDICO")
                        .requestMatchers(HttpMethod.POST, "/autenticacao/medico").hasAnyRole("ROLE_MEDICO")
                        .requestMatchers(HttpMethod.PUT, "/autenticacao/medico").hasAnyRole("ROLE_MEDICO")
                        .requestMatchers(HttpMethod.PUT, "/autenticacao/paciente").hasAnyRole("ROLE_MEDICO")

                        .requestMatchers(HttpMethod.DELETE, "/consulta/").hasAnyRole("ROLE_MEDICO", "ROLE_PACIENTE")
                        .requestMatchers(HttpMethod.POST, "/consulta/").hasAnyRole("ROLE_MEDICO", "ROLE_PACIENTE")
                        .requestMatchers(HttpMethod.GET, "/consulta/all").hasAnyRole("ROLE_MEDICO", "ROLE_PACIENTE")
                        .requestMatchers(HttpMethod.PUT, "/consulta/date").hasAnyRole("ROLE_MEDICO", "ROLE_PACIENTE")

                        .requestMatchers(HttpMethod.PUT, "/pagamento/pagar").hasAnyRole("ROLE_MEDICO", "ROLE_PACIENTE")
                        .requestMatchers(HttpMethod.POST, "/pagamento/cartao").hasAnyRole("ROLE_MEDICO", "ROLE_PACIENTE")
                        .requestMatchers(HttpMethod.DELETE, "/pagamento/cartao").hasAnyRole("ROLE_MEDICO", "ROLE_PACIENTE")

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
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
