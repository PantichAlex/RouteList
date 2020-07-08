package ru.pantich.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.pantich.formsobjects.RouteForm;
import ru.pantich.service.UserService;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/routes")
public class RouteController {

    @Autowired
    UserService userService;

    @GetMapping
    public String addRoute(Model model, HttpServletRequest request){
        String companyTitle="Название компании";
        model.addAttribute("page_routes", true);
        model.addAttribute("companyTitle", companyTitle);
        model.addAttribute("newRoute", new RouteForm());
        String login=request.getUserPrincipal().getName();
        model.addAttribute("isSpecialist", userService.isSpecialist(login));
        return "route_list";
    }
}
