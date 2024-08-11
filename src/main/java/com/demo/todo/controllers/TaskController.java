package com.demo.todo.controllers;
import com.demo.todo.models.Task;
import com.demo.todo.services.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {

    private static final Logger logger = LoggerFactory.getLogger(TaskService.class);

    @Autowired
    private TaskService taskService;

    @GetMapping(value = "/getall")
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    // add a task
    @PostMapping("/add")
    public Task addTask(@RequestBody Task task) {
        return taskService.addTask(task);
    }

    @DeleteMapping("/delete/{id}")
    public List<Task> deleteTask(@PathVariable int id) {
        return taskService.delete(id);
    }

    @PutMapping("/update/{id}")
    public Task updateTask(@PathVariable int id, @RequestBody Task task) {
        return taskService.updateTask(id, task.getDescription());
    }
}