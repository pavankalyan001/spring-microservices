package com.pavankalyan.taskservice.repository;

import com.pavankalyan.taskservice.model.Task;
import com.pavankalyan.taskservice.model.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByStatus(TaskStatus status);
    List<Task> findByAssigneeId(Long assigneeId);
}
