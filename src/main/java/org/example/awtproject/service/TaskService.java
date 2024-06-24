package org.example.awtproject.service;


import lombok.RequiredArgsConstructor;
import org.example.awtproject.dto.TaskDTO;
import org.example.awtproject.model.Project;
import org.example.awtproject.model.Task;
import org.example.awtproject.repo.TaskRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;


@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public Task saveTask(TaskDTO taskDTO, Project project){
        Task task = new Task();

        if(taskDTO.getDueDate() != null && !taskDTO.getDueDate().isEmpty()) {
            DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
            OffsetDateTime offsetDateTime = OffsetDateTime.parse(taskDTO.getDueDate(), formatter);
            LocalDate taskDueDate = offsetDateTime.toLocalDate();
            task.setDueDate(taskDueDate);
        }

        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setPriority(taskDTO.getPriority());
        task.setStatus(taskDTO.getStatus());
        task.setProject(project);

        return taskRepository.save(task);
    }

    public Task updateTask(TaskDTO taskDTO){
        Task task = taskRepository.findById(taskDTO.getId()).orElseThrow();

        if(taskDTO.getDueDate() != null && !taskDTO.getDueDate().isEmpty()) {
            DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
            OffsetDateTime offsetDateTime = OffsetDateTime.parse(taskDTO.getDueDate(), formatter);
            LocalDate taskDueDate = offsetDateTime.toLocalDate();
            task.setDueDate(taskDueDate);
        }

        if(taskDTO.getTitle() != null){
            task.setTitle(taskDTO.getTitle());
        }
        if(taskDTO.getDescription() != null){
            task.setDescription(taskDTO.getDescription());
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
