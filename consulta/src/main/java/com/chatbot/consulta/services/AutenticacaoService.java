package com.chatbot.consulta.services;

import com.chatbot.consulta.dtos.response.BaseResponseDto;
import com.chatbot.consulta.models.Especialidade;
import com.chatbot.consulta.models.Medico;
import com.chatbot.consulta.models.Paciente;
import com.chatbot.consulta.models.User;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class AutenticacaoService {

    public String criarNovoMedico(User usuario) {
        return null;
    }

    public List<Especialidade> buscarEspecialidades(List<Long> idEspecialidades) {
        return null;
    }

    public void emailJaCadastrado(String email) {
    }

    public User findUsuario(Long id) {
        return null;
    }

    public String deleteCascadeUsuario(Long id) {
        return null;
    }

    public BaseResponseDto criarMedicoUserAntigo(Medico medico) {
        return null;
    }

    public BaseResponseDto criarPacienteUserAntigo(Paciente paciente) {
        return null;
    }
}
