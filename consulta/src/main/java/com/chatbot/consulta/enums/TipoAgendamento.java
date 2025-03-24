package com.chatbot.consulta.enums;

import lombok.Getter;

@Getter
public enum TipoAgendamento {
    CONSULTA(0, 1, 150 ),
    RETORNO(1, 1, 0),
    EXAME(2, 1, 50),
    CIRURGIA(3, 24, 1000),
    INDISPONIVEL(4, 24, 0);

    private final int id;
    private final int horas;
    private final float valor;

    private TipoAgendamento(int id, int horas, float valor) {
        this.id = id;
        this.horas = horas;
        this.valor = valor;
    }
}
