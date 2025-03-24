package com.chatbot.consulta.services;

import com.chatbot.consulta.dtos.request.pagamento.PagamentoRequestDto;
import com.chatbot.consulta.dtos.response.PagamentoResponseDto;
import com.chatbot.consulta.enums.StatusPagamento;
import com.chatbot.consulta.models.Cartao;
import com.chatbot.consulta.repositories.ICartao;
import com.chatbot.consulta.repositories.IConsulta;
import com.chatbot.consulta.repositories.IMedico;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PagamentoService extends ConsultaService{

    @Autowired
    private ICartao cartaoRepository;

    public void existePendencias(Long idUsuario) {
        //TODO - verificar se existem consultas do usuario como paciente com o status pendente
        boolean temPendencias = consultaRepository.existsByPacienteIdAndStatusPagamento(idUsuario, StatusPagamento.PENDENTE);

        if (temPendencias) throw new RuntimeException("O paciente possui consultas com pagamento pendente!");
    }

    public String criarNovoCartao(Cartao cartao) {
        cartaoRepository.save(cartao);

        return "✅ Cartão cadastrado com sucesso!";
    }
}
