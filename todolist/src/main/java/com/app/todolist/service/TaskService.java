package com.app.todolist.service;

import com.app.todolist.entity.Task;
import com.app.todolist.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskrepo;

    public TaskService(TaskRepository taskrepo){
        this.taskrepo=taskrepo;
    }

    public List<Task> getAllTask() {
        List<Task> tasks= taskrepo.findAll();
        return (tasks!=null)?tasks:new ArrayList<>();
    }

    public void creatTask(String title) {
        Task task=new Task();
        task.setTitle(title);
        task.setCompleted(false);
        taskrepo.save(task);
    }

    public void deleteTask(Long id) {
        taskrepo.deleteById(id);
    }
    public void ToggleTask(Long id) {
        Task task=taskrepo.findById(id).orElseThrow(()-> new IllegalArgumentException("Invalid task id"));
        task.setCompleted(!task.isCompleted());
        taskrepo.save(task);
    }


    public void updateTask(Long id,String title) {
        Task task=taskrepo.findById(id).orElseThrow(()-> new IllegalArgumentException("Invalid task id"));
        task.setTitle(title);
        taskrepo.save(task);
    }

    public Task getTaskById(Long id) {
        return taskrepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with ID: " + id));
    }

}
