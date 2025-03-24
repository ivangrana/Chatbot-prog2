package com.chatbot.consulta.repositories;

import com.chatbot.consulta.models.Cartao;
import com.chatbot.consulta.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICartao extends JpaRepository<Cartao, Long> {
    boolean existsByIdAndUsuario(Long idCartao, Usuario usuario);
}
