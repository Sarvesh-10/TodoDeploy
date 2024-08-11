package com.demo.todo.services;


import com.demo.todo.models.Task;
import com.demo.todo.repositories.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private static final Logger logger = LoggerFactory.getLogger(TaskService.class);

    @Autowired
    private TaskRepository taskRepository;



    // add a task
    public Task addTask(Task task) {
        try {
            logger.info("upcoming task: {}", task);
            Task savedTask = taskRepository.save(task);
            logger.info("Task added successfully with ID: {}", savedTask.getId());
            return savedTask;
        } catch (Exception e) {
            logger.error("Data integrity violation occurred while adding task: {}", e.getMessage());
            return null;
        }
    }

    // delete a task
    public List<Task> delete(int id){
        try {
            taskRepository.deleteById(id);
            logger.info("Task deleted successfully with ID: {}", id);
        } catch (Exception e) {
            logger.error("Task not found with ID: {}", id);
        }
        return taskRepository.findAll();
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
//        return List.of(taskRepository.findAllOrderById());
    }

    public Task updateTask(int id, String description) {
        try {
            Optional<Task> optionalTask = taskRepository.findById(id);
           // If the task is present(not NULL), update the description and save it
            if (optionalTask.isPresent()) {
                Task task = optionalTask.get();
                task.setDescription(description);
                Task updatedTask = taskRepository.save(task);
                logger.info("Task updated successfully with ID: {}", updatedTask.getId());
                return updatedTask;
            } else {
                logger.error("Task not found with ID: {}", id);
                return null;
            }
        } catch (Exception e) {
            logger.error("Error occurred while updating task: {}", e.getMessage());
            return null;
        }
    }

}
