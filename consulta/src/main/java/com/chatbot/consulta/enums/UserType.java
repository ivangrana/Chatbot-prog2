package com.chatbot.consulta.enums;

import lombok.Getter;

@Getter
public enum UserType {
    ADMIN(0);

    private int id;

    private UserType(int id){
        this.id = id;
    }
}
