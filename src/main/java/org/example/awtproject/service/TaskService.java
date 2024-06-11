package org.example.awtproject.service;


import lombok.RequiredArgsConstructor;
import org.example.awtproject.dto.TaskDTO;
import org.example.awtproject.model.Project;
import org.example.awtproject.model.Task;
import org.example.awtproject.repo.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public Task saveProject(TaskDTO taskDTO, Project project){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate taskDueDate = LocalDate.parse(taskDTO.getDueDate(), formatter);
        Task task = new Task();

        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setDueDate(taskDueDate);
        task.setPriority(taskDTO.getPriority());
        task.setStatus(taskDTO.getStatus());
        task.setProject(project);
        return taskRepository.save(task);
    }

    public Task updateTask(TaskDTO taskDTO, Task task){
        if(taskDTO.getTitle() != null){
            task.setTitle(taskDTO.getTitle());
        }
        if(taskDTO.getDescription() != null){
            task.setDescription(taskDTO.getDescription());
        }
        if(taskDTO.getDueDate() != null){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate taskDueDate = LocalDate.parse(taskDTO.getDueDate(), formatter);
            task.setDueDate(taskDueDate);
        }
        if(taskDTO.getPriority() != null){
            task.setPriority(taskDTO.getPriority());
        }
        if(taskDTO.getStatus() != null){
            task.setStatus(taskDTO.getStatus());
        }

        return taskRepository.save(task);
    }
}
