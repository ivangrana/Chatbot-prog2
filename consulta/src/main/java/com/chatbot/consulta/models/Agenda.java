package com.chatbot.consulta.models;

import com.chatbot.consulta.enums.TipoAgendamento;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "agenda")
public class Agenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_inicial", nullable = false)
    private LocalDateTime dataInicial; // Data e hora específicos

    @Column(name = "data_final", nullable = false)
    private LocalDateTime dataFinal; // Data e hora específicos

    @Column(name = "tipo_agendamento", nullable = false)
    private TipoAgendamento tipoAgendamento;

    @ManyToOne
    @JoinColumn(name = "medico_id", nullable = false)
    private Medico medico;
}