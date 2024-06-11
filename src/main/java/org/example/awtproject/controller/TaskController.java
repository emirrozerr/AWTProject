package org.example.awtproject.controller;


import lombok.RequiredArgsConstructor;
import org.example.awtproject.dto.TaskDTO;
import org.example.awtproject.model.Project;
import org.example.awtproject.model.Task;
import org.example.awtproject.repo.ProjectRepository;
import org.example.awtproject.repo.TaskRepository;
import org.example.awtproject.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/tasks")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class TaskController {

    private final TaskRepository taskRepository;

    private final ProjectRepository projectRepository;

    private final TaskService taskService;

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<Task>> getAllTasks(@PathVariable Integer projectId){
        List<Task> tasks = taskRepository.findAllByProjectId(projectId);
        return ResponseEntity.status(HttpStatus.OK).body(tasks);
    }

    @PostMapping()
    public ResponseEntity<Task> saveTask(@RequestBody TaskDTO taskDTO){
        Project project = projectRepository.findById(taskDTO.getProjectId()).orElseThrow();
        Task task = taskService.saveProject(taskDTO,project);
        return ResponseEntity.status(HttpStatus.CREATED).body(task);
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<Task> updateTask(@PathVariable Integer taskId, @RequestBody TaskDTO taskDTO){
        Task task = taskRepository.findById(taskId).orElseThrow();
        task = taskService.updateTask(taskDTO,task);
        return ResponseEntity.status(HttpStatus.OK).body(task);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Task> deleteTask(@PathVariable Integer taskId){
        Task task = taskRepository.findById(taskId).orElseThrow();
        taskRepository.delete(task);
        return ResponseEntity.status(HttpStatus.OK).body(task);
    }
}
