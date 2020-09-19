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

    public Project getProjectByIdentifier(String projectId){
        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
        if (project ==null){
            throw new ProjectIdException("Project Id'"+projectId+"'does not exist");
        }
        return projectRepository.findByProjectIdentifier(projectId.toUpperCase());
    }

    public Iterable<Project> findAllProjects(){
        return projectRepository.findAll();
    }

    public void deleteProjectByIdentifier(String projectId){
        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());

        if(project == null){
            throw new ProjectIdException("Can not delete Project Id'"+projectId+"'.This Project Doesn't exist.");
        }
        projectRepository.delete(project);
    }

}
