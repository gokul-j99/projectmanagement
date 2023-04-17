/**
 * 
 */
package edu.neu.pmbackend.dao;

/**
 * @author gokuljayavel
 *
 */

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import edu.neu.pmbackend.entity.Backlog;
import edu.neu.pmbackend.entity.Project;


@Component
public class BacklogDAOImpl implements BacklogDAO {
	
	@PersistenceContext
	  private EntityManager entityManager;

	@Override
	@Transactional
	  public Backlog save(Backlog backlog) {
	    if (backlog.getId() == null) {
	      entityManager.persist(backlog);
	      return backlog;
	    } else {
	      return entityManager.merge(backlog);
	    }
	  }
	@Override
	  public Backlog findById(Long id) {
	    return entityManager.find(Backlog.class, id);
	  }
	@Override
	  public Backlog findByProjectIdentifier(String identifier) {
		  TypedQuery<Backlog> query = entityManager.createQuery("SELECT p FROM Backlog p WHERE p.projectIdentifier = :projectIdentifier", Backlog.class);
	        query.setParameter("projectIdentifier", identifier);
	        return query.getSingleResult();
	  }
	
		@Override
		@Transactional
		public void delete(Backlog backlog) {
			entityManager.remove(backlog);
		}

	}
