package ru.pantich.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.pantich.domain.Waybill;
import ru.pantich.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
@RequestMapping("/error")
public class ErrorController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String error(){


        return "error";
    }

    @GetMapping("/404")
    public String error404(Model model){

        model.addAttribute("message", "Страница не найдена");
        return "error_404";
    }


}
