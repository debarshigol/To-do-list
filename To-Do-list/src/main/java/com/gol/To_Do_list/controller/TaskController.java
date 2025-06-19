package com.gol.To_Do_list.controller;

import com.gol.To_Do_list.models.Task;
import com.gol.To_Do_list.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping
public class TaskController {

    @Autowired
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public String getTasks(Model model){
        try {
            List<Task> tasks = taskService.getAllTasks();
            // Model should be used
            // so that we can pass the data to the HTML page
            model.addAttribute("tasks", tasks);
            return "tasks";  // mapped html page name
        }
        catch (Exception e){
            return "No task is present!";
        }
    }
    @PostMapping
    public String addTasks(@RequestParam String title){
        taskService.addTask(title);
        return "redirect:/";
    }
    @GetMapping("{id}/delete")
    public String deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
        return "redirect:/";
    }
    @GetMapping("{id}/toggle")
    public String toggleTask(@PathVariable Long id){
        taskService.toggleTask(id);
        return "redirect:/";
    }
}
