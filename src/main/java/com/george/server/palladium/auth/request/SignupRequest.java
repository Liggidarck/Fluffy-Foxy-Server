package com.george.server.palladium.auth.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
public class SignupRequest {

    @NotBlank
    private String zone;

    @NotBlank
    private String name;

    @NotBlank
    private String lastName;

    @NotBlank
    private String patronymic;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    private Set<String> role;

}
