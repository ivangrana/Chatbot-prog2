package com.chatbot.consulta.repositories;

import com.chatbot.consulta.models.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IMedico extends JpaRepository<Medico, Long> {
}
