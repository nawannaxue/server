package com.nwnx.rs.dto.requests;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class UserRequest {

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @Email
    private String email;
    private String sex;
    private String password;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
