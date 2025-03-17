package com.chatbot.consulta.enums;

import lombok.Getter;


@Getter
public enum TipoUsuario {
    MEDICO(2),
    PACIENTE(4),
    MEDICO_PACIENTE(6);

    private final int id;

    private TipoUsuario(int id){
        this.id = id;
    }

    public void isMedico() {
        if (!(this == MEDICO || this == MEDICO_PACIENTE)) new RuntimeException("Não é médico");
    }

    public void isPaciente() {
        if (!(this == PACIENTE || this == MEDICO_PACIENTE)) new RuntimeException("Não é paciente");
    }
}
