package org.example.awtproject.controller;

import lombok.RequiredArgsConstructor;
import org.example.awtproject.dto.WorkspaceDTO;
import org.example.awtproject.model.User;
import org.example.awtproject.model.Workspace;
import org.example.awtproject.repo.UserRepository;
import org.example.awtproject.repo.WorkspaceRepository;
import org.example.awtproject.service.WorkspaceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController()
@RequestMapping("api/workspaces")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class WorkspaceController {

    private final WorkspaceRepository workspaceRepository;

    private final WorkspaceService workspaceService;

    private final UserRepository userRepository;

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Workspace>> getAllWorkspaces(@PathVariable UUID userId){
        List<Workspace> workspaces =  workspaceRepository.findAllByUserId(userId);
        return ResponseEntity.status(HttpStatus.OK).body(workspaces);
    }

    @PostMapping("/user/{userId}")
    public ResponseEntity<Workspace> saveWorkspace(@PathVariable UUID userId, @RequestBody WorkspaceDTO workspaceDTO){
        User user = userRepository.findById(userId).orElseThrow();
        Workspace workspace = workspaceService.saveWorkspace(workspaceDTO,user);
        return ResponseEntity.status(HttpStatus.CREATED).body(workspace);
    }

    @PutMapping("/{workspaceId}")
    public ResponseEntity<Workspace> updateWorkspace(@PathVariable Integer workspaceId, @RequestBody WorkspaceDTO workspaceDTO){
        Workspace workspace = workspaceRepository.findById(workspaceId).orElseThrow();
        workspace = workspaceService.updateWorkspace(workspaceDTO,workspace);
        return ResponseEntity.status(HttpStatus.CREATED).body(workspace);
    }

    @DeleteMapping("/{workspaceId}")
    public ResponseEntity<Workspace> deleteWorkspace(@PathVariable Integer workspaceId){
        Workspace workspace = workspaceRepository.findById(workspaceId).orElseThrow();
        workspaceRepository.delete(workspace);
        return ResponseEntity.status(HttpStatus.OK).body(workspace);
    }
}
