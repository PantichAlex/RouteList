package ru.pantich.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.pantich.domain.Car;
import ru.pantich.repo.CarRepo;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/cars")
public class CarsRestController {
    @Autowired
    CarRepo carRepo;

    @GetMapping("{likeName}")
    public ArrayList<Car> getCarsLike(@PathVariable String likeName,
                                      @RequestParam(defaultValue = "10") int limit){
        ArrayList<Car> cars=carRepo.findByNameStartsWith(likeName);
        if(cars.size()>limit){
            return new ArrayList<Car>(cars.subList(0,limit-1));
        }
        return cars;
    }
}
