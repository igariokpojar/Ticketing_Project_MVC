package com.cydeo.controller;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.service.ProjectService;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/project")
public class ProjectController {

    private final UserService userService;
    private final ProjectService projectService;

    public ProjectController(UserService userService, ProjectService projectService) {
        this.userService = userService;
        this.projectService = projectService;
    }

    @GetMapping("/create") // get the Page
    public String createProject(Model model) {// to activate the ProjectCreate button go to html fragments -> left sidebar line 40 -> th:href="@{/project/create}

        model.addAttribute("project", new ProjectDTO());
        // to add these to te view go to create html and bind this lines 35/40/54/68/70/86/98/112 + loop all the managers
        // to see only de managers I create new service in the UserService Interface findManager().. go to UserServiceImpl and implement the method
        model.addAttribute("managers", userService.findManagers());
        model.addAttribute("projects", projectService.findAll());

        return "/project/create";

    }

    @PostMapping("/create")
    public String insertProject(@Valid @ModelAttribute("project") ProjectDTO project, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {

            model.addAttribute("managers", userService.findManagers());
            model.addAttribute("projects", projectService.findAll());

            return "/project/create";

        }

        projectService.save(project);

        return "redirect:/project/create";

    }

    @GetMapping("/delete/{projectCode}")//  delete Button go to create html line 174-176
    public String deleteProject(@PathVariable("projectCode") String projectCode) {
        projectService.deleteById(projectCode);
        return "redirect:/project/create";
    }

    @GetMapping("/complete/{projectCode}") // whenever I click the complete button this end point needs to be executed
    public String completeProject(@PathVariable("projectCode") String projectCode) {
        // complete -> status to complete--> do I have Service doing this Job for me? nope! -> let's go and create this Service in ProjectService and implement the method in ProjectServiceImpl
        projectService.complete(projectService.findById(projectCode));
        return "redirect:/project/create"; //  it gives this view
    }

    @GetMapping("/update/{projectCode}")
    public String editProject(@PathVariable("projectCode") String projectCode, Model model){
        // we are doing this bc we need to populate all the Objects' field in the Form
        model.addAttribute("project", projectService.findById(projectCode));
        model.addAttribute("managers", userService.findManagers());
        model.addAttribute("projects", projectService.findAll());

        return "/project/update";

    }

    @PostMapping("/update") // after populating we are push the Save button and what happen after? go to update html form and check the "th:action="@{/project/update}" method="post"" is going to show what happen when you click on Save
    public String updateProject(@Valid @ModelAttribute("project") ProjectDTO project, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {

            model.addAttribute("managers", userService.findManagers());
            model.addAttribute("projects", projectService.findAll());

            return "/project/update"; // project create html line 174

        }

        projectService.update(project);

        return "redirect:/project/create";

    }

    @GetMapping("/manager/project-status")
    public String getProjectByManager(Model model) {

        UserDTO manager = userService.findById("john@cydeo.com");
        List<ProjectDTO> projects = projectService.getCountedListOfProjectDTO(manager);

        model.addAttribute("projects", projects);

        return "/manager/project-status";

    }

}