package com.chatbot.consulta.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "laudo")
public class Laudo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "consulta_id", nullable = false, unique = true)
    private Consulta consulta;

    @Column(nullable = false, length = 1000)
    private String diagnostico;

    @Column(length = 2000)
    private String observacoes;

    @Column(nullable = false)
    private LocalDateTime dataEmissao;
}
