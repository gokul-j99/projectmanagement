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

    void deleteById(String id);
    
    public Project saveOrupdate(Project user);
    
    
    List<Project> findAllByUserid(Long user_id);
}
