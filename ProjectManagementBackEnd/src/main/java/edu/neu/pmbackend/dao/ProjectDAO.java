package edu.neu.pmbackend.dao;

import java.util.List;

import edu.neu.pmbackend.entity.Project;
import edu.neu.pmbackend.entity.User;

/**
 * @author gokuljayavel
 *
 */

public interface ProjectDAO {

    Project findById(Long id);

    List<Project> findAllById(Long ids);

    Project findByProjectIdentifier(String projectIdentifier);

    List<Project> findAll();

    Project save(Project project);

    void deleteById(String id);
    
    public Project update(Project user);
    
    
    List<Project> findAllByUserid(Long user_id);
}
