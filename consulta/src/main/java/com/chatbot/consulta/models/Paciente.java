package com.chatbot.consulta.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "paciente", schema="public")
@Getter
@Setter
@NoArgsConstructor
public class Paciente extends User {

}
