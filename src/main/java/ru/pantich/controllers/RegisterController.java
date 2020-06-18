package ru.pantich.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.pantich.domain.User;
import ru.pantich.formsobjects.RegisterPOJO;
import ru.pantich.service.UserService;

@Controller
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    UserService userService;


    @GetMapping
    public String registerForm(Model model){
        model.addAttribute("register", new RegisterPOJO());
        return "register";
    }

    @PostMapping
    public String postRegForm(@ModelAttribute RegisterPOJO register, Model model){

        if(!register.getPassword().equals(register.getConfirmPassword())){

            model.addAttribute("errorPassword", "Пароли не совпадают");
            return "register";
        }
        User newUser=new User();
        newUser.setLogin(register.getLogin());
        newUser.setPassword(register.getPassword());

        userService.saveUser(newUser);

        return "redirect:/";

    }
}
