package com.chatbot.consulta.configs;

import com.chatbot.consulta.repositories.IUser;
import com.chatbot.consulta.services.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    TokenService tokenService;
    @Autowired
    IUser userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = this.recoverToken(request);

        if(token != null){

            var login = tokenService.validateToken(token);

            UserDetails user = userRepository.findByLogin(login);
            var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);

        }

        filterChain.doFilter(request, response);
    }
    @Bean
    public UserDetailsService userDetailsService() throws AuthenticationException {
        return username -> userRepository.findByLogin(username);
    }

    private String recoverToken(HttpServletRequest request) {

        var authHeader = request.getHeader("Authorization");

        if(authHeader == null) return null;

        return authHeader.replace("Bearer ", "");

    }
}
