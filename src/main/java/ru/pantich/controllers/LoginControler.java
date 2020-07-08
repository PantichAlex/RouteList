package ru.pantich.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.pantich.formsobjects.LoginForm;

@Controller
@RequestMapping("/login")
public class LoginControler {

    @GetMapping
    public String login(Model model){
        model.addAttribute("login", new LoginForm());
        return "login_page";
    }


}
