package com.chatbot.consulta.dtos.request.consulta;

import com.chatbot.consulta.models.Especialidade;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ConsultaRequestDto {
    @NotNull(message = "Campo idConsulta não pode ser nulo.", groups = {UpdateData.class, CancelConsulta.class})
    private Long idConsulta;

    @NotNull(message = "Campo idMedico não pode ser nulo.", groups = {ConsultaCreate.class})
    private Long idMedico;

    @NotNull(message = "Campo dataConsulta não pode ser nulo.", groups = {ConsultaCreate.class, UpdateData.class})
    private LocalDateTime dataConsulta;

    @NotNull(message = "Campo descricao não pode ser nulo.", groups = {ConsultaCreate.class})
    private String descricao;

    @NotNull(message = "Campo especialidade não pode ser nulo.", groups = {ConsultaCreate.class})
    private Especialidade especialidade;
}
