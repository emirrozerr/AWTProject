package org.example.awtproject.controller;

import lombok.RequiredArgsConstructor;
import org.example.awtproject.dto.ProjectDTO;
import org.example.awtproject.model.Project;
import org.example.awtproject.model.Workspace;
import org.example.awtproject.repo.ProjectRepository;
import org.example.awtproject.repo.WorkspaceRepository;
import org.example.awtproject.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("api/projects")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectRepository projectRepository;

    private final WorkspaceRepository workspaceRepository;

    private final ProjectService projectService;

    @GetMapping("/workspace{workspaceId}")
    public ResponseEntity<List<Project>> getAllProjects(@PathVariable Integer workspaceId){
        List<Project> projects = projectRepository.findAllByWorkspaceId(workspaceId);
        return ResponseEntity.status(HttpStatus.OK).body(projects);
    }

    @PostMapping("/workspace/{workspaceId}")
    public ResponseEntity<Project> saveProject(@PathVariable Integer workspaceId, @RequestBody ProjectDTO projectDTO){
        Workspace workspace = workspaceRepository.findById(workspaceId).orElseThrow();
        Project project = projectService.saveProject(projectDTO,workspace);
        return ResponseEntity.status(HttpStatus.CREATED).body(project);
    }

    @PutMapping("/{projectId}")
    public ResponseEntity<Project> updateProject(@PathVariable Integer projectId, @RequestBody ProjectDTO projectDTO){
        Project project = projectRepository.findById(projectId).orElseThrow();
        project = projectService.updateProject(projectDTO,project);
        return ResponseEntity.status(HttpStatus.CREATED).body(project);
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<Project> deleteProject(@PathVariable Integer projectId){
        Project project = projectRepository.findById(projectId).orElseThrow();
        projectRepository.delete(project);
        return ResponseEntity.status(HttpStatus.OK).body(project);
    }
}
