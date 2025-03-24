package com.chatbot.consulta.repositories;

import com.chatbot.consulta.models.Agenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface IAgenda extends JpaRepository<Agenda, Long> {
    boolean existsAgendaByMedicoIdAndDataInicialBetween(Long idMedico, LocalDateTime dataConsulta, LocalDateTime localDateTime);


}
