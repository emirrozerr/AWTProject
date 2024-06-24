package org.example.awtproject.service;

import lombok.RequiredArgsConstructor;
import org.example.awtproject.dto.WorkspaceDTO;
import org.example.awtproject.model.Project;
import org.example.awtproject.model.User;
import org.example.awtproject.model.Workspace;
import org.example.awtproject.repo.ProjectRepository;
import org.example.awtproject.repo.TaskRepository;
import org.example.awtproject.repo.WorkspaceRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkspaceService {

    private final WorkspaceRepository workspaceRepository;
    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;

    public Workspace saveWorkspace(WorkspaceDTO workspaceDTO, User user){

        Workspace workspace = new Workspace();
        workspace.setName(workspaceDTO.getName());
        workspace.setDescription(workspaceDTO.getDescription());
        workspace.setUser(user);

        return workspaceRepository.save(workspace);
    }

    public Workspace updateWorkspace(WorkspaceDTO workspaceDTO,Workspace workspace){
        if(workspaceDTO.getName() != null){
            workspace.setName(workspaceDTO.getName());
        }
        if(workspaceDTO.getDescription() != null){
            workspace.setDescription(workspaceDTO.getDescription());
        }
        return workspaceRepository.save(workspace);
    }

    @Transactional
    public void deleteWorkspace(Integer workspaceId){
        List<Project> projects = projectRepository.findAllByWorkspaceId(workspaceId);

        // Delete all tasks associated with each project
        for (Project project : projects) {
            taskRepository.deleteByProjectId(project.getId());
        }

        // Delete all projects associated with the workspace
        projectRepository.deleteByWorkspaceId(workspaceId);

        // Delete the workspace itself
        workspaceRepository.deleteById(workspaceId);
    }
}
