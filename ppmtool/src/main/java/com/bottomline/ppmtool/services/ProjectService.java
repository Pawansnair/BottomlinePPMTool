package com.bottomline.ppmtool.services;

import com.bottomline.ppmtool.domain.Backlog;
import com.bottomline.ppmtool.domain.Project;
import com.bottomline.ppmtool.exception.ProjectIdException;
import com.bottomline.ppmtool.repositories.BacklogRepository;
import com.bottomline.ppmtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    BacklogRepository backlogRepository;

    public Project saveOrUpdateProject(Project project){
        String identifier = project.getProjectIdentifier().toUpperCase();
        try{
            project.setProjectIdentifier(identifier);

            if(project.getId()==null){
                Backlog backlog =new Backlog();
                project.setBacklog(backlog);
                backlog.setProject(project);
                backlog.setProjectIdentifier(identifier);
            }
            if(project.getId()!=null){
                project.setBacklog(backlogRepository.findByProjectIdentifier(identifier));
            }

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
