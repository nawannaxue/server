package com.nwnx.services;

import com.nwnx.persistence.entities.User;

public interface UserService {
	User getUser(long userId);
	User getUser(String email);
	User addUser(String firstName, String lastName, String email, String sex, String password);
}
