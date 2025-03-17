package com.chatbot.consulta.services;

import com.chatbot.consulta.enums.StatusPagamento;
import com.chatbot.consulta.repositories.IConsulta;
import com.chatbot.consulta.repositories.IMedico;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PagamentoService {

    @Autowired
    private IConsulta consultaRepository;

    public void existePendencias(Long idUsuario) {
        //TODO - verificar se existem consultas do usuario como paciente com o status pendente
        boolean temPendencias = consultaRepository.existsByPacienteIdAndStatusPagamento(idUsuario, StatusPagamento.PENDENTE);

        if (temPendencias) throw new RuntimeException("O paciente possui consultas com pagamento pendente!");
    }
}
