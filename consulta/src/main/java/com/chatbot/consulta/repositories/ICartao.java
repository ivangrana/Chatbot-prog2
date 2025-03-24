package com.chatbot.consulta.repositories;

import com.chatbot.consulta.models.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICartao extends JpaRepository<Cartao, Long> {
}
