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

    @PostMapping()
    public ResponseEntity<Workspace> saveWorkspace(@RequestBody WorkspaceDTO workspaceDTO){
        UUID userId = UUID.fromString(workspaceDTO.getUserId());
        User user = userRepository.findById(userId).orElseThrow();
        Workspace workspace = workspaceService.saveWorkspace(workspaceDTO,user);
        return ResponseEntity.status(HttpStatus.CREATED).body(workspace);
    }

    @PutMapping("/{workspaceId}")
    public ResponseEntity<Workspace> updateWorkspace(@PathVariable Integer workspaceId, @RequestBody WorkspaceDTO workspaceDTO){
        Workspace workspace = workspaceRepository.findById(workspaceId).orElseThrow();
        Workspace updatedWorkspace = workspaceService.updateWorkspace(workspaceDTO,workspace);
        return ResponseEntity.ok(updatedWorkspace);
    }

    @DeleteMapping("/{workspaceId}")
    public ResponseEntity<String> deleteWorkspace(@PathVariable Integer workspaceId){
        workspaceService.deleteWorkspace(workspaceId);
        return ResponseEntity.ok("Successfully deleted workspace");
    }
}
