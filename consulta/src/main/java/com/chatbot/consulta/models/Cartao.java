package com.chatbot.consulta.models;

import com.chatbot.consulta.enums.TipoCartao;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Cartao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(name = "numero_cartao",nullable = false, length = 16, unique = true)
    private String numeroCartao;

    @Column(name = "nome_titular", nullable = false)
    private String nomeTitular;

    @Column(name = "validade", nullable = false, length = 5) // Formato MM/YY
    private String validade;

    @Column(name = "cvv", nullable = false, length = 3)
    private String cvv;

    @Column(name = "tipo_pagamento", nullable = false, length = 10)
    private TipoCartao tipoCartao; // "CREDITO" ou "DEBITO"

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime created_at = LocalDateTime.now();

    public Cartao(Usuario usuario, String numeroCartao, String nomeTitular, String validade, String cvv, TipoCartao tipoCartao) {
        this.usuario = usuario;
        this.numeroCartao = numeroCartao;
        this.nomeTitular = nomeTitular;
        this.validade = validade;
        this.cvv = cvv;
        this.tipoCartao = tipoCartao;
    }
}
