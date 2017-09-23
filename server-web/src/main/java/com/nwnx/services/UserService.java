package com.nwnx.services;

import com.nwnx.persistence.entities.User;
import com.nwnx.rs.dto.requests.UserRequest;

public interface UserService {
	User getUser(long userId);
	User getUser(String email);
	User addUser(UserRequest userRequest);
}
