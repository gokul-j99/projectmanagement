package edu.neu.pmbackend.repositry;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.neu.pmbackend.entity.Project;

@Repository
public interface ProjectRepositry extends CrudRepository<Project, Long>{
	
	@Override
   Iterable<Project> findAllById(Iterable<Long> iterable);
	
   Project findByProjectIdentifier(String projectIdentifier);
   
   Iterable<Project> findAll();
   
   @Override
   default void deleteById(Long id) {
	
	
}
	
	
}
