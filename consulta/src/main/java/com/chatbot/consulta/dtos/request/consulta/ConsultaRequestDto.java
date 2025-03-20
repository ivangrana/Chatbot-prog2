package com.chatbot.consulta.dtos.request.consulta;

import com.chatbot.consulta.dtos.request.autenticacao.*;
import com.chatbot.consulta.models.Especialidade;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ConsultaRequestDto {
    @NotNull(message = "Campo idMedico não pode ser nulo.", groups = {ConsultaCreate.class})
    private Long idMedico;

    @NotNull(message = "Campo dataConsulta não pode ser nulo.", groups = {ConsultaCreate.class})
    private LocalDateTime dataConsulta;

    @NotNull(message = "Campo descricao não pode ser nulo.", groups = {ConsultaCreate.class})
    private String descricao;

    @NotNull(message = "Campo especialidade não pode ser nulo.", groups = {ConsultaCreate.class})
    private Especialidade especialidade;
}
