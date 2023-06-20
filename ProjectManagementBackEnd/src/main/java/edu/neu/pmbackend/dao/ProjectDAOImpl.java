package edu.neu.pmbackend.dao;



import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Component;

import edu.neu.pmbackend.entity.Project;
import edu.neu.pmbackend.entity.User;

/**
 * @author gokuljayavel
 *
 */

@Component
public class ProjectDAOImpl implements ProjectDAO {

    @PersistenceContext
    private EntityManager entityManager;
    

    @Override
    public Project findById(Long id) {
        return entityManager.find(Project.class, id);
    }

    @Override
    public List<Project> findAllById(Long ids) {
    	TypedQuery<Project> query = entityManager.createQuery("SELECT p FROM Project p WHERE p.id IN :ids", Project.class);
        query.setParameter("ids", ids);
        return query.getResultList();
    }

    @Override
    public Project findByProjectIdentifier(String projectIdentifier) {
    	TypedQuery<Project> query = entityManager.createQuery("SELECT p FROM Project p WHERE p.projectIdentifier = :projectIdentifier", Project.class);
        query.setParameter("projectIdentifier", projectIdentifier);
        return query.getSingleResult();
    }

    @Override
    public List<Project> findAll() {
    	TypedQuery<Project> query = entityManager.createQuery("SELECT p FROM Project p", Project.class);
        return query.getResultList();
    }

//    @Override
//    @Transactional
//    public Project save(Project project) {
//    //	Session session = sessionFactory.openSession();
//    	System.out.println("before persist");
//    	entityManager.persist(project);
//        System.out.println("after persist");
//        return project;
//    }

	@Override
	@Transactional
	public void deleteById(String id) {
		//Session session = sessionFactory.openSession();
		// TODO Auto-generated method stub
		System.out.println("Inside dao");
		Query query1 = entityManager.createNativeQuery("DELETE FROM Backlog WHERE project_identifier = :projectIdentifier");
		query1.setParameter("projectIdentifier", id);
		query1.executeUpdate();
		//System.out.println(id);
		Query query = entityManager.createNativeQuery("DELETE FROM Project WHERE project_identifier = :id");
        query.setParameter("id", id);
        System.out.println(query);
        query.executeUpdate();
		
	}  
	
	@Transactional
	@Override
	public Project saveOrupdate(Project project) {
		
		System.out.println("Inside update");
		Project mergedProject;
		System.out.println(project);
		if(project.getId() != null) {
			System.out.println("Inside if update");
			ZoneId estZoneId = ZoneId.of("America/New_York");
			 ZonedDateTime estNow = LocalDateTime.now().atZone(estZoneId);
			 project.setUpdated_At(Date.from(estNow.toInstant()) );
		mergedProject = entityManager.merge(project);
			
		}
		else {
			mergedProject = project;
		entityManager.persist(project);
		}
		
        
        entityManager.flush();
        return mergedProject;
    }

	@Override
	public List<Project> findAllByUserid(Long user_id) {
		System.out.println("Before getting all project");
		System.out.println(user_id);
		
		String hql = "SELECT p FROM Project p WHERE p.user.id = :userId";
		Query query = entityManager.createQuery(hql);
		query.setParameter("userId", user_id);
		@SuppressWarnings("unchecked")
		List<Project> projects = query.getResultList();
		
//		TypedQuery<Project> query = entityManager.createQuery("SELECT p FROM Project p WHERE p.user = :user_id", Project.class);
//        query.setParameter("user_id", user_id);
//        System.out.println(query.getParameter("user_id")); 
        return projects;
	}
    
}
