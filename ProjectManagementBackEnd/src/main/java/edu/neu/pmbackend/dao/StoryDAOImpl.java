/**
 * 
 */
package edu.neu.pmbackend.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import edu.neu.pmbackend.entity.Story;

/**
 * @author gokuljayavel
 *
 */
public class StoryDAOImpl implements StoryDAO{
	
	@PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Story> findByProjectIdentifierOrderByPriority(String id) {
        TypedQuery<Story> query = entityManager.createQuery(
                "SELECT pt FROM ProjectTask pt WHERE pt.projectIdentifier = :id ORDER BY pt.priority",
                Story.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

    @Override
    public Story findByProjectSequence(String sequence) {
        TypedQuery<Story> query = entityManager.createQuery(
                "SELECT pt FROM ProjectTask pt WHERE pt.projectSequence = :sequence",
                Story.class);
        query.setParameter("sequence", sequence);
        try {
            return query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }
    
    
    @Override
    public Story save(Story projectTask) {
        if (projectTask.getId() == null) {
            entityManager.persist(projectTask);
            return projectTask;
        } else {
            return entityManager.merge(projectTask);
        }
    }

    @Override
    public void delete(Story projectTask) {
        entityManager.remove(projectTask);
    }

}
