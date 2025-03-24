package com.chatbot.consulta.services;

import com.chatbot.consulta.dtos.response.ConsultaResponseDto;
import com.chatbot.consulta.dtos.response.PagamentoResponseDto;
import com.chatbot.consulta.enums.StatusPagamento;
import com.chatbot.consulta.enums.TipoAgendamento;
import com.chatbot.consulta.models.Consulta;
import com.chatbot.consulta.models.Especialidade;
import com.chatbot.consulta.models.Medico;
import com.chatbot.consulta.models.Paciente;
import com.chatbot.consulta.repositories.*;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Transactional
public class ConsultaService extends AutenticacaoService{

    @Autowired
    protected IAgenda agendaRepository;

    @Autowired
    protected IMedico medicoRepository;

    @Autowired
    protected IConsulta consultaRepository;


    public void verificarAgendaMedico(Long idMedico, LocalDateTime dataConsulta) {
        if (agendaRepository.existsAgendaByMedicoIdAndDataInicialBetween(idMedico, dataConsulta, dataConsulta.plusHours(TipoAgendamento.CONSULTA.getHoras())))
            throw new RuntimeException("O médico não tem uma agenda disponível nessa data.");
    }

    public Object criarNovaConsulta(Consulta novaConsulta) {
        // 1. Salvar no banco
        consultaRepository.save(novaConsulta);
        // 2. Simular retorno dos dados de pagamento
        return new PagamentoResponseDto(novaConsulta.getId(), StatusPagamento.PENDENTE, TipoAgendamento.CONSULTA.getValor());
    }

    public Medico getMedicoById(Long idMedico) {
        return medicoRepository.findById(idMedico).orElseThrow(() -> new RuntimeException("Medico não encontrado!"));
    }

    public Paciente getPacienteById(Long idPaciente) {
        return pacienteRepository.findById(idPaciente).orElseThrow(() -> new RuntimeException("Paciente não encontrado!"));
    }

    public void verificarPrazoCriacao(LocalDateTime dataConsulta) {
        if (LocalDateTime.now().isAfter(dataConsulta.minusDays(1)))
            throw new RuntimeException("A consulta precisa ser marcada com pelo menos 24 horas de antecedência.");
    }

    public void verificarConsultaPacienteByAgendamento(Long idPaciente, LocalDateTime dataConsulta) {
        if (consultaRepository.existsAgendaByPacienteIdAndDataInicialBetween(
                    idPaciente,
                    dataConsulta,
                    dataConsulta.plusHours(TipoAgendamento.CONSULTA.getHoras())
            )
        ) throw new RuntimeException("O Paciente não tem uma agenda disponível nessa data.");
    }

    public void verificarEspecialidadeMedico(Medico medico, Especialidade especialidade) {
        if (medico.getEspecialidades().contains(especialidade))
            throw new RuntimeException("O médico não tem uma agenda disponível nessa data.");

    }

    public Consulta findConsulta(Long idPaciente, Long idConsulta) {
        return consultaRepository.findByIdAndPacienteId(idConsulta, idPaciente);
    }

    public ConsultaResponseDto atualizarDataConsulta(Consulta consulta) {
        consulta = consultaRepository.save(consulta);
        return new ConsultaResponseDto(
                consulta.getId(),
                consulta.getPaciente().getNome(),
                consulta.getAgendamento().getDataInicial(),
                consulta.getStatusPagamento()
        );
    }

    public void IsPacienteFromConsulta(Long idConsulta, Long idPaciente) {
        if (consultaRepository.existsByIdAndPacienteId(idConsulta, idPaciente))
            throw new RuntimeException("❌ Consulta não encontrada ou não pertence a este paciente.");
    }
}
