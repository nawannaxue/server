package com.nwnx.rs.dto.responses;

import java.time.ZonedDateTime;

public class UserResponse {
    private long id;

    private ZonedDateTime createdOn;

    private ZonedDateTime modifiedOn;

    private String fullName;

    private String name;

    private String email;

    private String password;

    public UserResponse(long id, ZonedDateTime createdOn, ZonedDateTime modifiedOn, String fullName, String name, String email, String password) {
        this.id = id;
        this.createdOn = createdOn;
        this.modifiedOn = modifiedOn;
        this.fullName = fullName;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public ZonedDateTime getCreatedOn() {
        return createdOn;
    }

    public ZonedDateTime getModifiedOn() {
        return modifiedOn;
    }

    public String getFullName() {
        return fullName;
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
}
