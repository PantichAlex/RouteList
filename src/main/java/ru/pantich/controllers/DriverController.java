package ru.pantich.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.pantich.domain.Driver;
import ru.pantich.formsobjects.DriverForm;
import ru.pantich.repo.DriverRepo;

@Controller
@RequestMapping("/driver")
public class DriverController {

    @Autowired
    DriverRepo driverRepo;

    @GetMapping("/add")
    public String addDriverForm(Model model){

        model.addAttribute("driver", new DriverForm());

        return "add_driver";
    }

    @PostMapping("/add")
    public String addDriver(@ModelAttribute DriverForm driverForm, Model model){
        boolean hasEmptyField=driverForm.getName().isEmpty();
        hasEmptyField|=driverForm.getSurname().isEmpty();
        hasEmptyField|=driverForm.getPatronymic().isEmpty();
        if(hasEmptyField){
            model.addAttribute("hasEmptyField", hasEmptyField);
            model.addAttribute("driver", driverForm);
            return "add_driver";
        }
        Driver newDriver=new Driver();
        newDriver.setName(driverForm.getName());
        newDriver.setSurname(driverForm.getSurname());
        newDriver.setPatronymic(driverForm.getPatronymic());
        driverRepo.save(newDriver);



        return "redirect:/home";
    }
}
