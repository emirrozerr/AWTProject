package org.example.awtproject.service;

import lombok.RequiredArgsConstructor;
import org.example.awtproject.dto.ProjectDTO;
import org.example.awtproject.model.Project;
import org.example.awtproject.model.Workspace;
import org.example.awtproject.repo.ProjectRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;

    public Project saveProject(ProjectDTO projectDTO, Workspace workspace){

        Project project = new Project();

        project.setName(projectDTO.getName());
        project.setDescription(projectDTO.getDescription());
        project.setWorkspace(workspace);
        return projectRepository.save(project);
    }

    public Project updateProject(ProjectDTO projectDTO, Project project){
        if(projectDTO.getName() != null){
            project.setName(projectDTO.getName());
        }
        if(projectDTO.getDescription() != null){
            project.setDescription(projectDTO.getDescription());
        }
        return projectRepository.save(project);
    }
}
