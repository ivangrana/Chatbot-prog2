package com.chatbot.consulta.repositories;

import com.chatbot.consulta.models.Calendario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ICalendario extends JpaRepository<Calendario, UUID> {
}
