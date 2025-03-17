package com.chatbot.consulta.models;

import com.chatbot.consulta.enums.TipoUsuario;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "paciente")
@Getter
@Setter
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "id")
public class Paciente extends Usuario {
    @Column(name = "plano_saude")
    private String planoSaude;

    @OneToMany
    @JoinColumn(name = "medico_id")
    private List<Consulta> consultas;

    public Paciente(String name, String email, String password, int idade, String planoSaude) {
        super(name, email, TipoUsuario.PACIENTE, password, idade);
        this.planoSaude = planoSaude;
    }
    public Paciente(Usuario usuario, String planoSaude) {
        super(usuario.getId(), usuario.getNome(), usuario.getEmail(), TipoUsuario.PACIENTE, usuario.getPassword(), usuario.getIdade());
        this.planoSaude = planoSaude;
    }
}
