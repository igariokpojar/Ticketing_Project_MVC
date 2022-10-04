package com.cydeo.controller;

import com.cydeo.dto.TaskDTO;
import com.cydeo.service.ProjectService;
import com.cydeo.service.TaskService;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/task")
public class TaskController {

    private final ProjectService projectService;
    private final UserService userService;
    private final TaskService taskService;

    public TaskController(ProjectService projectService, UserService userService, TaskService taskService) {
        this.projectService = projectService;
        this.userService = userService;
        this.taskService = taskService;
    }

    @GetMapping("/create")// but before tu use it we need to injected // look up we did
    public String createTask(Model model){ // 3 thinks we need to past to HTML
    model.addAttribute("task",new TaskDTO()); // 1. Task Objects
    model.addAttribute("projects",projectService.findAll());// 2. Projects
    model.addAttribute("employees", userService.findEmployees()); // 3. Employees // go to UserService and implement this Method "findEmployee"
        model.addAttribute("tasks",taskService.findAll()); // go to table and add  see the attribute Task


       return "task/create";
    }

 // this method will execute when we click on Save Button
    @PostMapping("/create") // we're filling the Object and POSTING
    public String insertTask(TaskDTO task){

        taskService.save(task); // ask your self if we have Service?

         return "redirect:/task-create";
    }

    @GetMapping("/task/delete/{id}") // go to HTML and see what they put here for delete //task/delete/{id}
    public String deleteTask(@PathVariable("id") Long id){

        taskService.deleteById(id);

        return "redirect:/task-create";
    }

    @GetMapping("/update/{taskId}") // go to create.html and find the Button Update
    public String editTask(@PathVariable("taskId") Long taskId,Model model) {
        // pass all the Object which is coming from Service
        model.addAttribute("task",taskService.findById(taskId));
        model.addAttribute("projects",projectService.findAll());
        model.addAttribute("employees",userService.findEmployees());
        model.addAttribute("tasks",taskService.findAll());

        return "/task/update";
    }

//    @PostMapping("update/{taskId}")
//    public String updateTask(@PathVariable("taskId") Long taskId,TaskDTO task){
//
//        task.setId(taskId);
//        taskService.update(task);
//
//        return "redirect:/task-create";
//    }

    @PostMapping("/update/{id}")
    public String updateTask(TaskDTO task){

               taskService.update(task);

        return "redirect:/task-create";
    }


}
