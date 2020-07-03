package ru.pantich.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.pantich.formsobjects.RouteForm;

@Controller
@RequestMapping("/routes")
public class RouteController {

    @GetMapping("/add_route")
    public String addRoute(Model model){
        String companyTitle="Название компании";
        model.addAttribute("companyTitle", companyTitle);
        model.addAttribute("newRoute", new RouteForm());

        return "add_route";
    }
}
