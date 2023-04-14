package edu.neu.pmbackend.dao;



import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Component;

import edu.neu.pmbackend.entity.Project;

@Component
public class ProjectDAOImpl implements ProjectDAO {

    @PersistenceContext
    private EntityManager entityManager;
    

    @Override
    public Project findById(Long id) {
        return entityManager.find(Project.class, id);
    }

    @Override
    public List<Project> findAllById(Iterable<Long> ids) {
    //	Session session = sessionFactory.openSession();
    	TypedQuery<Project> query = entityManager.createQuery("SELECT p FROM Project p WHERE p.id IN :ids", Project.class);
        query.setParameter("ids", ids);
        return query.getResultList();
    }

    @Override
    public Project findByProjectIdentifier(String projectIdentifier) {
    //	Session session = sessionFactory.openSession();
    	System.out.println("Inside projid");
    	TypedQuery<Project> query = entityManager.createQuery("SELECT p FROM Project p WHERE p.projectIdentifier = :projectIdentifier", Project.class);
        query.setParameter("projectIdentifier", projectIdentifier);
        return query.getSingleResult();
    }

    @Override
    public List<Project> findAll() {
    	//Session session = sessionFactory.openSession();
    	TypedQuery<Project> query = entityManager.createQuery("SELECT p FROM Project p", Project.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public Project save(Project project) {
    //	Session session = sessionFactory.openSession();
    	System.out.println("before persist");
    	entityManager.persist(project);
        System.out.println("after persist");
        return project;
    }

	@Override
	@Transactional
	public void deleteById(Integer id) {
		//Session session = sessionFactory.openSession();
		// TODO Auto-generated method stub
		System.out.println("Inside dao");
		//System.out.println(id);
		TypedQuery<Project> query = entityManager.createQuery("SELECT p FROM Project p WHERE p.id = :id", Project.class);
        query.setParameter("id", id);
        System.out.println(query);
        Project proj = query.getSingleResult();
		System.out.println(proj);
      if (proj != null) {
    	  System.out.println("Inside if");
    	  entityManager.remove(proj);
      }
		
	}  
	
	@Transactional
	@Override
	public Project update(Project user) {
		
		System.out.println("Inside update");
		Project mergedProject;
		System.out.println(user);
		if(user.getId() != null) {
			System.out.println("Inside if update");
		mergedProject = entityManager.merge(user);
			
		}
		else {
			mergedProject = user;
		entityManager.persist(user);
		}
		
        
        entityManager.flush();
        return mergedProject;
    }
    
}
