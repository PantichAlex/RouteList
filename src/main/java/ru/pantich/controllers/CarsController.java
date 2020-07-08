package ru.pantich.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.pantich.domain.Car;
import ru.pantich.repo.CarRepo;

@Controller
@RequestMapping("/cars")
public class CarsController {

    @Autowired
    private CarRepo carRepo;

    @GetMapping("/add_car")
    public String addCar(Model model){
        model.addAttribute("car", new Car());
        return "add_car";
    }

    //TODO: Не забудь дописать обработку пустой формы
    @PostMapping("/add_car")
    public String addCarr(@ModelAttribute Car car, Model model){
        Car carFromDb=carRepo.findByNumber(car.getNumber());
        if(car.getNumber().length()==0 || car.getName().length()==0 ){
            return "add_car";
        }
        if(carFromDb != null){
            car.setNumber("");
            model.addAttribute("car", car);
            model.addAttribute("exists", true);
            return "add_car";

        }

        carRepo.save(car);
        return "redirect:/home";
    }





}
