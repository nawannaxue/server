package com.nwnx.rs.dto.requests;

import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Optional;

public class UserRequest {

    private String fullName;

    @NotNull
    private String name;

    @Email
    private String email;

    @NotNull
    private String password;

    @JsonCreator
    public UserRequest(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Optional<String> getFullName() {
        return Optional.ofNullable(fullName);
    }
}
