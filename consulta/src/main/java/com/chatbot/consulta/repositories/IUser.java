package com.chatbot.consulta.repositories;

import com.chatbot.consulta.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IUser extends JpaRepository<User, UUID> {
    UserDetails findByLogin(Object login);
}
