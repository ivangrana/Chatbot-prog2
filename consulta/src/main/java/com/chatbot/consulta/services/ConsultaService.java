package com.chatbot.consulta.services;

import com.chatbot.consulta.models.Especialidade;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Transactional
public class ConsultaService {
    public void verificarAgendaMedico(Long idMedico, LocalDateTime dataConsulta) {
    }

    public void verificarAgendaPaciente(Long id, LocalDateTime dataConsulta) {
    }

    public void verificarEspecialidadeMedico(Long idMedico, Especialidade especialidade) {
    }
}
