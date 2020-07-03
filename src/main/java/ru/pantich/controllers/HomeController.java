package ru.pantich.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
@RequestMapping("/home")
public class HomeController {


    @GetMapping
    public String home(Model model, HttpServletRequest request){

        Principal principal=request.getUserPrincipal();
        model.addAttribute("username", principal.getName());
        return "home";
    }
}
