package com.chatbot.consulta.dtos.response;

import com.chatbot.consulta.enums.StatusPagamento;

public record PagamentoResponseDto(Long id, StatusPagamento statusPagamento, float valor){
}
