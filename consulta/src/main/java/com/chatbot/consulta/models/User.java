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
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", updatable = true)
    private String name;
    @Column(name = "login", updatable = true)
    private String login;
    @Column(name = "user_type")
    private TipoUsuario userType;
    @Column(name = "password")
    private String password;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> roles = new ArrayList<>();

        if(userType == TipoUsuario.ADMIN) roles.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        if (userType.equals(TipoUsuario.PACIENTE)) roles.add(new SimpleGrantedAuthority("ROLE_PACIENTE"));
        if (userType.equals(TipoUsuario.ADMIN_PACIENTE)) {
            roles.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
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
        return login;
    }
}
