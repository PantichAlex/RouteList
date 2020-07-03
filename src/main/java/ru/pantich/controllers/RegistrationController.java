package ru.pantich.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.pantich.domain.User;
import ru.pantich.formsobjects.RegisterForm;
import ru.pantich.service.UserService;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String registration(Model model){
        model.addAttribute("register", new RegisterForm());
        model.addAttribute("error", "");
        return "registration";
    }

    @PostMapping
    public String registerNewUser(@ModelAttribute RegisterForm register, Model model){

        if(register.getPassword().length()<4){
            RegisterForm registerForm =new RegisterForm();
            registerForm.setLogin(register.getLogin());
            model.addAttribute("register", registerForm);
            model.addAttribute("error", "Слишком короткий пароль");
            return "register";
        }
        if(!register.getPassword().equals(register.getConfirmPassword())){
            RegisterForm registerForm =new RegisterForm();
            registerForm.setLogin(register.getLogin());
            model.addAttribute("register", registerForm);
            model.addAttribute("error", "Пароли не совпадают");
            return "register";
        }
        User user=new User();
        user.setLogin(register.getLogin());
        user.setPassword(register.getPassword());
        boolean saveUser=userService.saveUser(user);
        if(!saveUser){
            model.addAttribute("register",new RegisterForm());
            model.addAttribute("error", "Такой пользователь уже существует");
            return "register";
        }
        return "redirect:/login";
    }
}
