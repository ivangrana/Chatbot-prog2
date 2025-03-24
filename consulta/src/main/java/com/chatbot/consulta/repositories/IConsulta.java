package com.chatbot.consulta.repositories;

import com.chatbot.consulta.enums.StatusPagamento;
import com.chatbot.consulta.models.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface IConsulta extends JpaRepository<Consulta, Long> {
    boolean existsByPacienteIdAndStatusPagamento(Long idUsuario, StatusPagamento statusPagamento);

    @Query("SELECT COUNT(c) > 0 FROM Consulta c WHERE c.paciente.id = :idPaciente AND c.agenda.dataInicial BETWEEN :dataInicio AND :dataFim")
    boolean existsAgendaByPacienteIdAndAgendaDataInicialBetween(Long idPaciente, LocalDateTime dataConsulta, LocalDateTime dataFim);

    Consulta findByIdAndPacienteId(Long idConsulta, Long idPaciente);

    boolean existsByIdAndPacienteId(Long idConsulta, Long idPaciente);

    boolean existsByPacienteId(Long idPaciente);

    List<Consulta> findByPacienteId(Long idPaciente);
}
