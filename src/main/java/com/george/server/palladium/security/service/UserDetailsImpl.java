package com.george.server.palladium.security.service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.george.server.palladium.model.user.UserPalladium;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserDetailsImpl implements UserDetails {

    private static final long serialVersionUID = 1L;
    private final Long id;
    private final String zone;
    private final String name;
    private final String lastName;
    private final String patronymic;
    private final String email;

    private final String username;
    @JsonIgnore
    private final String password;

    private final Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(Long id, String zone, String name, String lastName, String patronymic, String email, String password, String username,
                           Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.zone = zone;
        this.name = name;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.email = email;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserDetailsImpl build(UserPalladium user) {
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());

        return new UserDetailsImpl(
                user.getId(),
                user.getZone(),
                user.getName(),
                user.getLastName(),
                user.getPatronymic(),
                user.getEmail(),
                user.getPassword(),
                user.getUsername(),
                authorities);
    }

    public Long getId() {
        return id;
    }

    public String getZone() {
        return zone;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null || getClass() != obj.getClass())
            return false;

        UserDetailsImpl user = (UserDetailsImpl) obj;
        return Objects.equals(id, user.id);
    }

}
