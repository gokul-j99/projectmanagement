/**
 * 
 */
package edu.neu.pmbackend.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
//import javax.transaction.Transactional;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.stereotype.Component;

import edu.neu.pmbackend.entity.Project;
import edu.neu.pmbackend.entity.Story;

/**
 * @author gokuljayavel
 *
 */

@Component
public class StoryDAOImpl implements StoryDAO{
	
	@PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Story> findByProjectIdentifierOrderByPriority(String id) {
        TypedQuery<Story> query = entityManager.createQuery(
                "SELECT pt FROM Story pt WHERE pt.projectIdentifier = :id ORDER BY pt.priority",
                Story.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

    @Override
    public Story findByProjectSequence(String sequence) {
        TypedQuery<Story> query = entityManager.createQuery(
                "SELECT pt FROM Story pt WHERE pt.projectSequence = :sequence",
                Story.class);
        query.setParameter("sequence", sequence);
        try {
            return query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }
    
    
    @Override
    @Transactional
    public Story save(Story projectTask) {
        if (projectTask.getId() == null) {
            entityManager.persist(projectTask);
            return projectTask;
        } else {
            return entityManager.merge(projectTask);
        }
    }

    @Override
    @Transactional
    public void delete(Story projectTask) {
        entityManager.remove(projectTask);
    }
    
    
    @Transactional
	@Override
	public Story update(Story story) {
		System.out.println("Inside update");
		entityManager.merge(story);
        return story;
    }

	@Override
	public List<Story> findByProjectIdentifierDev(String id) {
        TypedQuery<Story> query = entityManager.createQuery(
                "SELECT pt FROM Story pt WHERE pt.projectIdentifier = :id and pt.storyType = :storytype  ORDER BY pt.priority",
                Story.class);
        query.setParameter("storytype", "Dev");
        query.setParameter("id", id);
        return query.getResultList();
	}

	@Override
	@Transactional
	public List<Story> findByDueDate(Date date) {
		
		
		String hql = "SELECT pt FROM Story pt";
		Date today = new Date();
		Query query = entityManager.createQuery(hql, Story.class);
		//query.setParameter("today", date, TemporalType.DATE);
		List<Story> stories = query.getResultList();
		
		System.out.println(stories);

		// TODO Auto-generated method stub
//		
//		System.out.println(date);
//        TypedQuery<Story> query = entityManager.createQuery(
//                "SELECT pt FROM Story pt WHERE pt.dueDate = :tomorrow",
//                Story.class);
//        query.setParameter("tomorrow", date);
        return stories;
	}

}
