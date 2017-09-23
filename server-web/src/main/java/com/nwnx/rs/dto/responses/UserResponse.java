package com.nwnx.rs.dto.responses;

import java.util.Date;

public class UserResponse {
    private long id;

    private Date createdOn;

    private Date modifiedOn;

    private String fullName;

    private String name;

    private String email;

    private String password;

    public UserResponse(long id, Date createdOn, Date modifiedOn, String fullName, String name, String email, String password) {
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

    public Date getCreatedOn() {
        return createdOn;
    }

    public Date getModifiedOn() {
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
