package com.chatbot.consulta.services;

import com.chatbot.consulta.dtos.response.BaseResponseDto;
import com.chatbot.consulta.models.Especialidade;
import com.chatbot.consulta.models.Medico;
import com.chatbot.consulta.models.Paciente;
import com.chatbot.consulta.models.User;
import com.chatbot.consulta.repositories.IEspecialidade;
import com.chatbot.consulta.repositories.IMedico;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class AutenticacaoService {

    @Autowired
    private IEspecialidade especialidadeRepository;

    @Autowired
    private IMedico medicoRepository;

    public String criarNovoMedico(Medico medico) {

        medicoRepository.save(medico);

        return "Médico " + medico.getNome() + " cadastrado com sucesso!";
    }

    public List<Especialidade> buscarEspecialidades(List<Long> idEspecialidades) {
        List<Especialidade> response = new ArrayList<>();
        for (Long id: idEspecialidades) {
            Especialidade especialidade = especialidadeRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Especialidade não encontrada"));
            response.add(especialidade);
        }
        return response;
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
