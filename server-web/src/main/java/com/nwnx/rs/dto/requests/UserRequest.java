package com.nwnx.rs.dto.requests;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Optional;

public class UserRequest {

    @NotNull
    private final String name;

    @Email
    private final String email;

    @NotNull
    private final String password;

    private String fullName;

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
