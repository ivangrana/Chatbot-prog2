package com.chatbot.consulta.repositories;

import com.chatbot.consulta.models.Especialidade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IEspecialidade extends JpaRepository<Especialidade, Long> {
}
