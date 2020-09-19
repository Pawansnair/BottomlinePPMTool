package com.bottomline.ppmtool.services;

import com.bottomline.ppmtool.domain.Project;
import com.bottomline.ppmtool.exception.ProjectIdException;
import com.bottomline.ppmtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    ProjectRepository projectRepository;

    public Project saveOrUpdateProject(Project project){
        try{
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projectRepository.save(project);
        }catch (Exception e){
            throw new ProjectIdException("Project Id'"+project.getProjectIdentifier().toUpperCase()+"'already exist");
        }

    }
}
