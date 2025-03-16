package com.chatbot.consulta.dtos.request.Auth;
import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class AuthRequestDto {
    @NotNull(message = "Campo name não pode ser nulo.", groups = {MedicoCreate.class, PacienteCreate.class})
    private String name;

    @NotNull(message = "Campo idade não pode ser nulo.", groups = {MedicoCreate.class, PacienteCreate.class})
    private int idade;

    @NotNull(message = "Campo email não pode ser nulo.", groups = {LoginRequest.class, MedicoCreate.class, PacienteCreate.class})
    private String email;

    @NotNull(message = "Campo password não pode ser nulo.", groups = {LoginRequest.class, MedicoCreate.class, PacienteCreate.class})
    private String password;
}
