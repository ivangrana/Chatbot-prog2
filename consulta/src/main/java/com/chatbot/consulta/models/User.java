package com.chatbot.consulta.models;

import com.chatbot.consulta.enums.TipoUsuario;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "user", schema="public")
@Getter
@Setter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@EqualsAndHashCode(of = "id")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome")
    private String nome;
    @Column(name = "email")
    private String email;
    @Column(name = "idade")
    private int idade;
    @Column(name = "tipo_usuario")
    private TipoUsuario tipoUsuario;
    @Column(name = "password")
    private String password;

    public User(String nome, String email, TipoUsuario tipoUsuario, String password, int idade){
        this.nome = nome;
        this.email = email;
        this.tipoUsuario = tipoUsuario;
        this.password = password;
        this.idade = idade;
    }

    public User(Long id, String nome, String email, TipoUsuario tipoUsuario, String password, int idade){
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.tipoUsuario = tipoUsuario;
        this.password = password;
        this.idade = idade;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> roles = new ArrayList<>();

        if(tipoUsuario == TipoUsuario.MEDICO) roles.add(new SimpleGrantedAuthority("ROLE_MEDICO"));
        if (tipoUsuario.equals(TipoUsuario.PACIENTE)) roles.add(new SimpleGrantedAuthority("ROLE_PACIENTE"));
        if (tipoUsuario.equals(TipoUsuario.MEDICO_PACIENTE)) {
            roles.add(new SimpleGrantedAuthority("ROLE_MEDICO"));
            roles.add(new SimpleGrantedAuthority("ROLE_PACIENTE"));
        }
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }
}
