package ru.pantich.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.pantich.domain.User;
import ru.pantich.domain.UserRole;
import ru.pantich.repo.UserRepo;
import ru.pantich.service.UserPrincipal;
import ru.pantich.service.UserService;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private UserService userService;


    @GetMapping
    public String home(Model model, HttpServletRequest request){
        String login=request.getUserPrincipal().getName();
        model.addAttribute("isSpecialist", userService.isSpecialist(login));

        return "home";
    }
}
