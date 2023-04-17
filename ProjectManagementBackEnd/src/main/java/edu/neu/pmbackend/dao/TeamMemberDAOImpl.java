/**
 * 
 */
package edu.neu.pmbackend.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import edu.neu.pmbackend.entity.TeamMember;

/**
 * @author gokuljayavel
 *
 */
public class TeamMemberDAOImpl implements TeammemberDAO{
	
	
	
	
	

    @PersistenceContext
    private EntityManager entityManager;

	@Override
	public TeamMember findByUsername(String username) {
		

		TypedQuery<TeamMember> query = entityManager.createQuery("SELECT u FROM User u WHERE u.username = :username", TeamMember.class);
       query.setParameter("username", username);
        return query.getResultList().stream().findFirst().orElse(null);
	}

	@Override
	public TeamMember getById(Long id) {
		// TODO Auto-generated method stub
		 return entityManager.find(TeamMember.class, id);
	}

	@Override
	public TeamMember save(TeamMember user) {
		System.out.println("before persist");
		 
    	entityManager.persist(user);
        System.out.println("after persist");
        return user;
	}

}
