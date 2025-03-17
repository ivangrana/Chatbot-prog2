package com.chatbot.consulta.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "paciente", schema="public")
@Getter
@Setter
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "id")
public class Paciente extends User {
    @Column(name = "plano_saude")
    private String planoSaude;

    @OneToMany
    @JoinColumn(name = "medico_id")
    private List<Consulta> consultas;

}
