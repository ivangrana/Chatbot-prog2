package com.chatbot.consulta.dtos.response;

import com.chatbot.consulta.enums.StatusPagamento;

import java.time.LocalDateTime;

public record ConsultaResponseDto(Long id, String paciente, LocalDateTime dataConsulta, StatusPagamento statusPagamento) {
}
