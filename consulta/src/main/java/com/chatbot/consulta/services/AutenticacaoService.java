package com.chatbot.consulta.services;

import com.chatbot.consulta.dtos.response.BaseResponseDto;
import com.chatbot.consulta.models.Especialidade;
import com.chatbot.consulta.models.Medico;
import com.chatbot.consulta.models.Paciente;
import com.chatbot.consulta.models.Usuario;
import com.chatbot.consulta.repositories.IEspecialidade;
import com.chatbot.consulta.repositories.IMedico;
import com.chatbot.consulta.repositories.IPaciente;
import com.chatbot.consulta.repositories.IUsuario;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AutenticacaoService {

    @Autowired
    private IEspecialidade especialidadeRepository;

    @Autowired
    private IMedico medicoRepository;

    @Autowired
    private IUsuario usuarioRepository;

    @Autowired
    private IPaciente pacienteRepository;

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
        if (usuarioRepository.existsByEmail(email)) throw new RuntimeException("E-mail já cadastrado!");
    }

    public Usuario findUsuario(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));
    }

    public BaseResponseDto deleteCascadeUsuario(Usuario usuario) {
        // Remove o usuário (se os relacionamentos estiverem configurados corretamente, as dependências também serão removidas)
        usuarioRepository.delete(usuario);
        return  new BaseResponseDto("Usuário e seus dados relacionados foram excluídos com sucesso!");
    }

    public BaseResponseDto criarMedicoUserAntigo(Medico medico) {
        medicoRepository.save(medico);
        return new BaseResponseDto("Médico criado com sucesso!");
    }

    public BaseResponseDto criarPacienteUserAntigo(Paciente paciente) {
        usuarioRepository.save(paciente);
        return new BaseResponseDto("Paciente criado com sucesso!");
    }

    public String criarNovoPaciente(Paciente paciente) {
        pacienteRepository.save(paciente);
        return "Paciente " + paciente.getNome() + " cadastrado com sucesso!";
    }
}
