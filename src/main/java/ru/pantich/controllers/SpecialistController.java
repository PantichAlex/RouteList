package ru.pantich.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.pantich.domain.Specialist;
import ru.pantich.domain.User;
import ru.pantich.formsobjects.SpecialistForm;
import ru.pantich.repo.CarRepo;
import ru.pantich.repo.SpecialistRepo;
import ru.pantich.repo.UserRepo;

import java.security.Principal;

@Controller
@RequestMapping("/specialist")
public class SpecialistController {

    @Autowired
    UserRepo userRepo;

    @Autowired
    SpecialistRepo specialistRepo;

    @GetMapping
    public String specialistForm(Model model, Principal principal){

        String login=principal.getName();
        User userFromdb=userRepo.findByLogin(login);
        Specialist specialist=specialistRepo.findByUser(userFromdb);
        SpecialistForm specialistForm=new SpecialistForm();
        if(specialist!=null){
            specialistForm.setName(specialist.getName());
            specialistForm.setSurname(specialist.getSurname());
            specialistForm.setPatronymic(specialist.getPatronymic());
        }else{

            model.addAttribute("message", "Вам необходимо заполнить карту специалиста");
        }
        model.addAttribute("specialist",specialistForm);

        return "specialist";
    }

    @PostMapping
    public String saveSpecialist(@ModelAttribute SpecialistForm specialistForm, Model model, Principal principal){

        boolean hasEmptyField=specialistForm.getName().isEmpty();
        hasEmptyField|=specialistForm.getSurname().isEmpty();
        hasEmptyField|=specialistForm.getPatronymic().isEmpty();
        if(hasEmptyField){
            model.addAttribute("message", "Не все поля заполнены");
            model.addAttribute("specialist", specialistForm);
            return "specialist";
        }
        String login=principal.getName();
        User userFromdb=userRepo.findByLogin(login);
        Specialist specialist=specialistRepo.findByUser(userFromdb);
        if(specialist==null){
            specialist=new Specialist();
        }
        specialist.setName(specialistForm.getName());
        specialist.setSurname(specialistForm.getSurname());
        specialist.setPatronymic(specialistForm.getPatronymic());
        specialist.setUser(userFromdb);

        specialistRepo.save(specialist);
        return "redirect:/home";
    }
}
