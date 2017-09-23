package com.nwnx.persistence.repositories.impl;

import com.nwnx.persistence.entities.User;
import com.nwnx.persistence.repositories.UserDAO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Service("userDAO")
@Transactional(propagation=Propagation.REQUIRED)
public class UserDAOImpl implements UserDAO {
	
	@PersistenceContext
	public EntityManager entityManager;

	@Transactional
	public User addUser(User user) {
		entityManager.persist(user);
		return user;
	}

	@Transactional(readOnly=true)
	public User getUser(long id) {
		 Query query  = entityManager.createQuery("select user from User user where user.id=:id");
		 query.setParameter("id", id);
		 return (User)query.getSingleResult();
	}

	@Transactional(readOnly=true)
	public User getUser(String email) {
		return (User) entityManager.createQuery("select user from User user where"
				+ " user.email=:email").setParameter("email", email).getSingleResult();
	}
}
