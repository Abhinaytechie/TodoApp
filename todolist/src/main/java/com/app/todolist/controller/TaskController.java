package com.app.todolist.controller;

import com.app.todolist.entity.Task;
import com.app.todolist.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/tasks")
public class TaskController {



    private final TaskService service;
    public TaskController(TaskService service){
        this.service=service;
    }

    @GetMapping
    public String getTasks(Model model){
        List<Task> tasks= service.getAllTask();
        model.addAttribute("tasks",tasks);
        return "tasks";
    }
    @PostMapping
    public String createTask(@RequestParam String title){
        service.creatTask(title);
        return "redirect:/tasks";
    }
    @GetMapping("/{id}/delete")
    public String getTasks(@PathVariable Long id){
        service.deleteTask(id);
        return "redirect:/tasks";
    }
    @GetMapping("/{id}/toggle")
    public String toggleTask(@PathVariable Long id){
        service.ToggleTask(id);
        return "redirect:/tasks";
    }
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Task task = service.getTaskById(id); // Fetch task details
        model.addAttribute("task", task);
        return "edit-task"; // Thymeleaf template for editing
    }
    @PostMapping("/update")
    public String updateTask(@ModelAttribute Task task) {
        service.updateTask(task.getId(), task.getTitle()); // Call the service method
        return "redirect:/tasks"; // Redirect to the main task list
    }



}
