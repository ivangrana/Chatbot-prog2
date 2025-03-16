package com.chatbot.consulta.enums;

import lombok.Getter;


@Getter
public enum TipoUsuario {
    ADMIN(2),
    PACIENTE(4),
    ADMIN_PACIENTE(6);

    private final int id;

    private TipoUsuario(int id){
        this.id = id;
    }
}
