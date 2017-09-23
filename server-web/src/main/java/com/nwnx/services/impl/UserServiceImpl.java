package com.nwnx.services.impl;

import com.nwnx.persistence.entities.User;
import com.nwnx.persistence.repositories.UserDAO;
import com.nwnx.rs.dto.requests.UserRequest;
import com.nwnx.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
@Transactional(propagation = Propagation.REQUIRED)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    public User getUser(long userId) {
        return userDAO.getUser(userId);
    }

    public User getUser(String email) {
        return userDAO.getUser(email);
    }

    public User addUser(UserRequest userRequest) {
        User user = new User(userRequest.getName(), userRequest.getEmail(), userRequest.getPassword());
       userRequest.getFullName().ifPresent(user::setFullName);
        return userDAO.addUser(user);
    }
}
