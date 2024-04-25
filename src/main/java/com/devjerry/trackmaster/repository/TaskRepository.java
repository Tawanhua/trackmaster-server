package com.devjerry.trackmaster.repository;

import com.devjerry.trackmaster.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
