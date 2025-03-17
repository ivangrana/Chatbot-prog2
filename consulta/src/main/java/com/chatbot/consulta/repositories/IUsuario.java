package com.chatbot.consulta.repositories;

import com.chatbot.consulta.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsuario extends JpaRepository<Usuario, Long> {
    boolean existsByEmail(String email);

    UserDetails findByEmail(String login);
}
