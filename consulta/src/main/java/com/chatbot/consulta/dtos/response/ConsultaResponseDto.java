package com.chatbot.consulta.dtos.response;

import com.chatbot.consulta.enums.StatusPagamento;
import lombok.Getter;

import java.time.LocalDateTime;

public record ConsultaResponseDto(Long id, String paciente, LocalDateTime dataConsulta, StatusPagamento statusPagamento, String mensagem, String nomeMedico) {
}
