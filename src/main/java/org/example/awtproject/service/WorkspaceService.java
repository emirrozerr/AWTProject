package org.example.awtproject.service;

import lombok.RequiredArgsConstructor;
import org.example.awtproject.dto.WorkspaceDTO;
import org.example.awtproject.model.User;
import org.example.awtproject.model.Workspace;
import org.example.awtproject.repo.WorkspaceRepository;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WorkspaceService {

    private final WorkspaceRepository workspaceRepository;

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
}
