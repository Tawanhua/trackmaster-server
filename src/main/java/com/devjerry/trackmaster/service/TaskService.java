package com.devjerry.trackmaster.service;

import com.devjerry.trackmaster.exception.TaskNotFoundException;
import com.devjerry.trackmaster.model.Task;
import com.devjerry.trackmaster.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService implements ITaskService {
    private final TaskRepository taskRepository;

    @Override
    public Task addTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public List<Task> getTask() {
        return taskRepository.findAll();
    }

    @Override
    public Task updateTask(Task task, Long id) {
        return taskRepository.findById(id).map(tk -> {
            tk.setName(task.getName());
            tk.setTitle(task.getTitle());
            tk.setDescription(task.getDescription());
            tk.setDuration(task.getDuration());
            tk.setDueDate(task.getDueDate());
            return taskRepository.save(tk);
        }).orElseThrow(() -> new TaskNotFoundException("Sorry, task could not be found."));
    }

    @Override
    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Sorry, task not found with Id: " + id));
    }

    @Override
    public void deleteTask(Long id) {
        if(!taskRepository.existsById(id)) {
            throw new TaskNotFoundException("Sorry, task not found.");
        }
    }
}