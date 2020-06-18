package ru.pantich.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.pantich.formsobjects.LoginPOJO;

@Controller
@RequestMapping("/login")
public class LoginControler {

    @GetMapping
    public String login(Model model){
        model.addAttribute("login", new LoginPOJO());
        return "login";
    }

    @PostMapping
    public String checkLogin(@ModelAttribute LoginPOJO login, Model model){


        return "debug";

    }
}
