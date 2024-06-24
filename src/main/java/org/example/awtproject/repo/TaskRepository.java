package org.example.awtproject.repo;

import org.example.awtproject.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Integer> {
    List<Task> findAllByProjectId(Integer projectId);
    void deleteByProjectId(Integer projectId);
}
