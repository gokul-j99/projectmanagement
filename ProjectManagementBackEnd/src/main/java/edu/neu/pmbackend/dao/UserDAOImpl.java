/**
 * 
 */
package edu.neu.pmbackend.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
//import org.hibernate.query.Query;
import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.cfg.Configuration;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import edu.neu.pmbackend.entity.User;

/**
 * @author gokuljayavel
 *
 */

@Component
public class UserDAOImpl implements UserDAO{
	
	

    @PersistenceContext
    private EntityManager entityManager;
	
   
    @Override
	public User findByUsername(String username) {
    //	Session session = sessionFactory.openSession();

		TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class);
       query.setParameter("username", username);
        return query.getResultList().stream().findFirst().orElse(null);
	}

	@Override
	public User getById(Long id) {
		// TODO Auto-generated method stub
		 return entityManager.find(User.class, id);
	}
	
	  	@Override
	    @Transactional
	    public User save(User user) {
	    	System.out.println("before persist");
	 
	    	entityManager.persist(user);
	        System.out.println("after persist");
	        return user;
	    }
	

}
