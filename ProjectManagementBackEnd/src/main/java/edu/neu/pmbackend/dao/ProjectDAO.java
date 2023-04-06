package edu.neu.pmbackend.dao;

import java.util.List;

import edu.neu.pmbackend.entity.Project;

public interface ProjectDAO {

    Project findById(Long id);

    List<Project> findAllById(Iterable<Long> ids);

    Project findByProjectIdentifier(String projectIdentifier);

    List<Project> findAll();

    Project save(Project project);

    void deleteById(Integer id);
    
}
