package com.chatbot.consulta.models;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

@Entity
@Table(name = "medico", schema="public")
@Getter
@Setter
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "id")
public class Medico extends User {

    @Column(name = "crm")
    private String crm;

    @ManyToMany
    @JoinTable(
            name = "medico_especialidade", // Tabela intermediária
            joinColumns = @JoinColumn(name = "medico_id"),
            inverseJoinColumns = @JoinColumn(name = "especialidade_id")
    )
    private List<Especialidade> especialidades;

    @OneToMany(mappedBy = "medico", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Agenda> agendamentos;

    @Column(name = "trabalha_sabado", nullable = false)
    private Boolean trabalhaSabado;

    @Column(name = "trabalha_domingo", nullable = false)
    private Boolean trabalhaDomingo;

    @OneToMany(mappedBy = "medico", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Consulta> consultas;

}
