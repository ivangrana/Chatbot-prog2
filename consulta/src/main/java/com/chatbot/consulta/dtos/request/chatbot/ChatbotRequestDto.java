package com.chatbot.consulta.dtos.request.chatbot;

import com.chatbot.consulta.dtos.request.consulta.CancelConsulta;
import com.chatbot.consulta.dtos.request.consulta.UpdateData;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ChatbotRequestDto {
    @NotNull(message = "Desculpe, não entendi o que disse.")
    private String mensagem;
}