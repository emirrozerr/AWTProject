package org.example.awtproject.repo;

import org.example.awtproject.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Integer> {
    List<Project> findAllByWorkspaceId(Integer workspaceId);
}
