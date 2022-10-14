package com.cydeo.controller;

import com.cydeo.dto.UserDTO;
import com.cydeo.service.RoleService;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

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
    // Model is an Interface that is carrying Data to view(can be Object,String Array ...)
    public String createUser(Model model){

        // I'm passing an empty Object bc when we are open the page one empty page(Object) must come... (form on HTML)
        model.addAttribute("user",new UserDTO()); //"user" attribute is holding the userObject

        //  to bring the info from DB we need Service to find all the roles from DB(businessLogic) (role on HTML)
        model.addAttribute("roles",roleService.findAll());

        model.addAttribute("users",userService.findAll()); // table on HTML

        return "/user/create"; // go to HTML and look  in the Form (if we have a form then we need an Object)what kind of Object? First Name,Last Name and bla bla bla..this Object need to come from Controller
        // if you return same view, you have to provide in a method all the attributes required for the html file to show it
        // whatever view needs(object, lists etc) need to be provided again in a new method
    }



    // when ever you click ths SaveBottom id going to come here and is going to execute this method and is going back to "/create"
    @PostMapping("/create") // this method is going to post or to Inject the User in DB or "SaveButton"
    public String insertUser(@Valid @ModelAttribute("user") UserDTO user, BindingResult bindingResult, Model model){ // capture the User

        if (bindingResult.hasErrors()) {

            model.addAttribute("roles", roleService.findAll());
            model.addAttribute("users", userService.findAll());

            return "/user/create";

        }
        // bring the Object "user" form SAVE method by using @ModelAttribute ("user") annotation UserDTO user
        userService.save(user); // in DB save and update is not going to be the same

        return "redirect:/user/create"; //end point when you redirect

    }
     // when ever I click on Update Button I need to go in DB request the Object and population (we need to see population Data, All the Roles and All the Users coming from the Map)
    @GetMapping("/update/{username}") // to bring something from html to our code we need PathVariable or Query
    public String editUser(@PathVariable("username") String username, Model model){ // to be able to create an attribute we need Model

        model.addAttribute("user", userService.findById(username)); // to bring something from DB we need Service
        // need to go to DB take a required object and get the user
        // if you do any action that required connection with DB you need to go to service

       model.addAttribute("roles",roleService.findAll());

        model.addAttribute("users",userService.findAll());

        return "/user/update"; // should UpdateButton work we need to Link on the update html line 211
    }

   @PostMapping("/update") // do we have service user updating Object?..nope we don't have so let's create one.. on the CrudService
    public String updateUser(@Valid @ModelAttribute("user") UserDTO user, BindingResult bindingResult, Model model){ // @ModelAttribute is optional

       if (bindingResult.hasErrors()) {

           model.addAttribute("roles", roleService.findAll());
           model.addAttribute("users", userService.findAll());

           return "/user/update";

       }
      userService.update(user);

        return "redirect:/user/create";
   }

   @GetMapping("/delete/{username}") // with user, we are deleting
   public String deleteUser(@PathVariable("username")String username){ // by using @PahVariable we are capture the username

       userService.deleteById(username);

       return "redirect:/user/create"; // go to  create or update html and update the Button line 212
    }



}
