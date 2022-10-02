package com.cydeo.controller;

import com.cydeo.dto.UserDTO;
import com.cydeo.service.RoleService;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    private final RoleService roleService; // injection in order to use the classes and methods within this class
    private final UserService userService;

    public UserController(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @GetMapping("/create")
    public String createUser(Model model){ // Model is carrying Data to view

        model.addAttribute("user",new UserDTO()); // form on HTML

        model.addAttribute("roles",roleService.findAll()); // role on HTML

        model.addAttribute("users",userService.findAll()); // table on HTML

        return "/user/create";
        // if you return same view, you have to provide in a method all the attributes required for the html file to show it
        // whatever view needs(object, lists etc) need to be provided again in a new method
    }




    @PostMapping("/create") // this method is going to post or to Inject the User in DB
    public String insertUser(@ModelAttribute("user") UserDTO user){ // capture the User

        userService.save(user);

        return "redirect:/user/create"; //end point when you redirect

    }
    @GetMapping("/update/{username}") // to bring something from html to our code we need PathVariable or Query
    public String editUser(@PathVariable("username") String username, Model model){

        model.addAttribute("user", userService.findById(username));
        // need to go to DB take a required object and get the user
        // if you do any action that required connection with DB you need to go to service

       model.addAttribute("roles",roleService.findAll());

        model.addAttribute("users",userService.findAll());

        return "/user/update";
    }

   @PostMapping("/update")
    public String updateUser(@ModelAttribute("user") UserDTO user){

      userService.update(user);

        return "redirect:/user/create";
   }

   @GetMapping("/delete/{username}")
   public String deleteUser(@PathVariable("username")String username){

       userService.deleteById(username);

       return "redirect:/user/create";
    }



}
