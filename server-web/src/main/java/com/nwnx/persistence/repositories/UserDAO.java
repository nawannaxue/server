package com.nwnx.persistence.repositories;

import com.nwnx.persistence.entities.User;
import org.springframework.stereotype.Repository;

@Repository("userDao")
public interface UserDAO {
	User addUser(User user);
	User getUser(long userId);
	User getUser(String email);
}
