package com.br.gerenciarAtividades.domain.Models;

import com.br.gerenciarAtividades.domain.Dtos.user.RegistrarUserDTO;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Document(collection = "Users")
public class User implements UserDetails {
    @Id
    private String id;

    @Column(unique = true)
    private String login;

    private String email;

    private String password;

    public User(RegistrarUserDTO dto) {
        this.login = dto.login();
        this.email = dto.email();
        this.password = dto.password();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
