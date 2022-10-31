package com.george.fluffy.foxy.server.auth.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
public class JwtResponse {

    private String token;
    private String type = "Bearer";
    private Long id;
    private String username;

    private String zone;

    private String name;
    private String lastName;
    private String patronymic;
    private String email;
    private List<String> roles;

    public JwtResponse(String token, Long id, String username, String zone, String name, String lastName, String patronymic, String email, List<String> roles) {
        this.token = token;
        this.id = id;
        this.username = username;
        this.zone = zone;
        this.name = name;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.email = email;
        this.roles = roles;
    }
}
