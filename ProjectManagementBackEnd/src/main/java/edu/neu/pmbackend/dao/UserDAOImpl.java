/**
 * 
 */
package edu.neu.pmbackend.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
import javax.transaction.Transactional;

import edu.neu.pmbackend.entity.Project;
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
    	
    	System.out.println(" before persist username");
    	System.out.println(username);

		TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class);
       query.setParameter("username", username);
       System.out.println(" after persist username");
       User user = query.getResultList().stream().findFirst().orElse(null);
       System.out.println(user);
        return user;
	}

	@Override
	public User getById(Long id) {
		// TODO Auto-generated method stub
		User user  =entityManager.find(User.class, id);
		System.out.println(user);
		 return user;
	}
	
	  	@Override
	    @Transactional
	    public User save(User user) {
	    	System.out.println("before persist");
	 
	    	entityManager.persist(user);
	        System.out.println("after persist");
	        return user;
	    }

		@Override
		public List<User> fetchUserByManagerid(Long id) {
			TypedQuery<User> query = entityManager.createQuery("SELECT  u FROM User u WHERE u.manager_id = :manager_id", User.class);
			query.setParameter("manager_id",id);
	        return query.getResultList();
		}

		@Override
		public void deleteById(Long id) {
			
			Query query = entityManager.createNativeQuery("DELETE FROM User WHERE id = :id");
	        query.setParameter("id", id);
	        query.executeUpdate();
		}
	

}
