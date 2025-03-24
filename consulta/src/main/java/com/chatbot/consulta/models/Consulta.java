package com.chatbot.consulta.models;

import com.chatbot.consulta.enums.StatusPagamento;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "consulta")
@Getter
@Setter
@NoArgsConstructor
public class Consulta {

    public static final int DAY = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "prazo_pagamento")
    private LocalDateTime prazoPagamento;

    @Column(name = "prazo_cancelamento")
    private LocalDateTime prazoCancelamento;

    @Column(name = "status_pagamento")
    private StatusPagamento statusPagamento;

    @ManyToOne
    @JoinColumn(name = "medico_id", nullable = false)
    private Medico medico;

    @ManyToOne
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "especialidade_id", nullable = false)
    private Especialidade especialidade;

    @Column(name = "cancelada")
    private Boolean isCancelada;

    @OneToOne
    @JoinColumn(name = "agenda_id", nullable = false, unique = true)
    private Agenda agendamento;

    public Consulta(Medico medico, Paciente paciente, Especialidade especialidade, Agenda agenda){
        this.medico = medico;
        this.paciente = paciente;
        this.especialidade = especialidade;
        this.agendamento = agenda;
        this.statusPagamento = StatusPagamento.PENDENTE;
        this.prazoPagamento = LocalDateTime.now().plusDays(DAY);
        this.prazoCancelamento = LocalDateTime.now().minusDays(DAY);
    }

}
