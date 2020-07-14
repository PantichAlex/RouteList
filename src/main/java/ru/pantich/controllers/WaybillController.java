package ru.pantich.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.pantich.domain.*;
import ru.pantich.formsobjects.RouteSheetForm;
import ru.pantich.formsobjects.WaybillForm;
import ru.pantich.repo.*;
import ru.pantich.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/waybill")
public class WaybillController {

    @Autowired
    private UserService userService;

    @Autowired
    private WaybillRepo waybillRepo;

    @Autowired
    private DriverRepo driverRepo;

    @Autowired
    private CarRepo carRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private SpecialistRepo specialistRepo;

    @Autowired
    private RouteSheetRepo routeSheetRepo;


    @GetMapping
    public String getWaybillList(Model model, HttpServletRequest request){


        String login=request.getUserPrincipal().getName();

        model.addAttribute("page_waybill", true);

        Iterable<Waybill> waybills= waybillRepo.findAll();
        model.addAttribute("waybills", waybills);
        return "waybill_list";
    }

    @GetMapping("/add")
    public String getWaybillForm(Model model, HttpServletRequest request){

        model.addAttribute("page_upload", true);
        String login=request.getUserPrincipal().getName();

        model.addAttribute("waybill", new WaybillForm());

        return "waybill_add";
    }


    @PostMapping("/add")
    public String createNewWaybill(@ModelAttribute WaybillForm waybillForm,
                                   Model model,
                                   HttpServletRequest request){



        Waybill newWaybill=new Waybill();
        newWaybill.setAddressDelivery(waybillForm.getAddress());
        newWaybill.setNameDelivery(waybillForm.getName());
        newWaybill.setPhone(waybillForm.getPhone());

        waybillRepo.save(newWaybill);
        return "redirect:/waybill";

    }

    @GetMapping("/{waybill_id}")
    public String getWaybillForm(
            @PathVariable("waybill_id") long waybill_id,
            Principal principal,
            Model model
    ){
        String login=principal.getName();
        User userFromdb=userRepo.findByLogin(login);
        Specialist specialist=specialistRepo.findByUser(userFromdb);

        if(specialist==null){
            return "redirect:/specialist";
        }

        Optional<Waybill> waybillFromDb=waybillRepo.findById(waybill_id);
        if(!waybillFromDb.isPresent()){
            return "redirect:/error/404";
        }
        Waybill waybill=waybillFromDb.get();
        RouteSheet waybillRouteSheet=waybill.getRouteSheet();
        RouteSheetForm routeSheetForm=new RouteSheetForm();
        if(waybillRouteSheet!=null) {
            Driver driver=waybillRouteSheet.getDriver();
            if(driver!=null) routeSheetForm.setDriverId(driver.getId());

        }


        List<Driver> drivers=driverRepo.findAll();
        List<Car> cars=carRepo.findAll();
        routeSheetForm.setSpecialistId(specialist.getId());

        model.addAttribute("routeSheet", routeSheetForm);
        model.addAttribute("waybill", waybill);
        model.addAttribute("drivers", drivers);
        model.addAttribute("cars",cars);
        model.addAttribute("specialist", specialist);
        return "waybill_change";

    }

    @PostMapping("/{waybill_id}")
    public String setWaybillWorkers(@ModelAttribute RouteSheetForm routeSheetForm,
                                    @PathVariable("waybill_id") long waybill_id,
                                    Principal principal,
                                    Model model){
        Optional<Driver> driverOptional=driverRepo.findById(routeSheetForm.getDriverId());
        Optional<Car> car=carRepo.findById(routeSheetForm.getCarId());
        User user=userRepo.findByLogin(principal.getName());
        Specialist specialist=specialistRepo.findByUser(user);
        Optional<Waybill> waybillOptional=waybillRepo.findById(waybill_id);


        if(!driverOptional.isPresent()
                || specialist==null
                || !car.isPresent()
                || !waybillOptional.isPresent()
        ){
            return "redirect:/error/404";
        }
        Waybill waybill=waybillOptional.get();
        RouteSheet routeSheet=new RouteSheet();
        routeSheet.setDriver(driverOptional.get());
        routeSheet.setSpecialist(specialist);
        routeSheet.setCar(car.get());
        routeSheet.setName(waybill.getNameDelivery()+" (накладная)");
        routeSheet.setDate(Calendar.getInstance().getTime());

        routeSheetRepo.save(routeSheet);
        waybill.setRouteSheet(routeSheet);
        waybillRepo.save(waybill);

        return "redirect:/waybill";
    }


}
