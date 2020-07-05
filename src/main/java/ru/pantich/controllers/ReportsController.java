package ru.pantich.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.pantich.service.UserService;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/reports")
public class ReportsController {

    @Autowired
    UserService userService;
    @GetMapping
    public String getReports(Model model, HttpServletRequest request){

        model.addAttribute("page_reports", true);
        String login=request.getUserPrincipal().getName();
        model.addAttribute("isSpecialist", userService.isSpecialist(login));

        return "reports";
    }
}
