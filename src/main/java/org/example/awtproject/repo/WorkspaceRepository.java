package org.example.awtproject.repo;

import org.example.awtproject.model.Workspace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface WorkspaceRepository extends JpaRepository<Workspace,Integer> {
    List<Workspace> findAllByUserId(UUID user_id);
}
