package ru.pantich.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.pantich.domain.*;
import ru.pantich.repo.*;
import ru.pantich.viewsPojos.CarReportPojo;
import ru.pantich.viewsPojos.DriverReportPojo;
import ru.pantich.viewsPojos.SpecialistReportPojo;
import ru.pantich.viewsPojos.WaybillReportPojo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/reports")
public class ReportsController {

    @Autowired
    private WaybillRepo waybillRepo;

    @Autowired
    private DriverRepo driverRepo;

    @Autowired
    private RouteSheetRepo routeSheetRepo;

    @Autowired
    private CarRepo carRepo;

    @Autowired
    private SpecialistRepo specialistRepo;

    @GetMapping
    public String getReports(Model model){
        model.addAttribute("page_reports", true);
        return "reports";
    }

    @GetMapping("/drivers")
    public String getDriversReports(Model model){
        List<Driver> drivers=driverRepo.findAll();
        model.addAttribute("drivers", drivers);
        return "report_drivers_list";
    }

    @GetMapping("/drivers/{driver_id}")
    public String getDriverReport(@PathVariable("driver_id") long driverId, Model model){


        Optional<Driver> driverFromdb=driverRepo.findById(driverId);
        if(!driverFromdb.isPresent()){
            return "redirect:/error/404";
        }

        Driver driver=driverFromdb.get();
        List<RouteSheet> routeSheets=routeSheetRepo.findByDriver(driver);

        ArrayList<Waybill> waybills=new ArrayList<>();

        for(RouteSheet routeSheet:routeSheets){
            Waybill waybill=waybillRepo.findByRouteSheet(routeSheet);
            if(waybill!=null){
                waybills.add(waybill);
            }
        }


        ArrayList<DriverReportPojo> driverReportPojos=new ArrayList<>();
        for(Waybill waybill: waybills){
            DriverReportPojo pojo=new DriverReportPojo();
            pojo.setNameDelivery(waybill.getNameDelivery());
            pojo.setAddress(waybill.getAddressDelivery());
            pojo.setPhone(waybill.getPhone());
            Car car=waybill.getRouteSheet().getCar();
            pojo.setCarName(car.getNumber()+" "+car.getName());
            Specialist specialist=waybill.getRouteSheet().getSpecialist();
            pojo.setSpecialist(specialist.getName()+" "+specialist.getPatronymic()+" "+specialist.getSurname());
            pojo.setDate(waybill.getRouteSheet().getDate());
            driverReportPojos.add(pojo);
        }


        model.addAttribute("driver",driver);

        model.addAttribute("driverReportPojos", driverReportPojos);
        return "driver_report";

    }

    @GetMapping("/waybills")
    private String getWaybillsReport(Model model){

        List<Waybill> waybills= (List<Waybill>) waybillRepo.findAll();
        model.addAttribute("waybills", waybills);

        return "report_waybills_list";
    }

    @GetMapping("/waybills/{waybill_id}")
    private String getWaybillReport(@PathVariable("waybill_id") long waybillId, Model model){

        Optional<Waybill> waybillFromDb=waybillRepo.findById(waybillId);
        if(!waybillFromDb.isPresent()){
            return "redirect:/error/404";
        }
        Waybill waybill=waybillFromDb.get();
        RouteSheet waybillRouteSheet=waybill.getRouteSheet();
        if(waybillRouteSheet==null){
            model.addAttribute("name", waybill.getNameDelivery());

            return "waybill_not_registed";
        }

        WaybillReportPojo waybillReportPojo=new WaybillReportPojo();

        waybillReportPojo.setAddress(waybill.getAddressDelivery());
        waybillReportPojo.setName(waybill.getNameDelivery());
        waybillReportPojo.setPhone(waybill.getPhone());
        Driver driver=waybillRouteSheet.getDriver();
        waybillReportPojo.setDriver(driver.getName()+" "+driver.getPatronymic()+" "+driver.getSurname());
        Specialist specialist=waybillRouteSheet.getSpecialist();
        waybillReportPojo.setSpecialist(specialist.getName()+" "+specialist.getPatronymic()+" "+specialist.getSurname());
        Car car=waybillRouteSheet.getCar();
        waybillReportPojo.setCar(car.getNumber()+" "+car.getName());
        waybillReportPojo.setDate(waybillRouteSheet.getDate());

        model.addAttribute("report", waybillReportPojo);

        return "waybill_report";
    }

    @GetMapping("/cars")
    private String carsReport(Model model){

        List<Car> cars=carRepo.findAll();
        model.addAttribute("cars", cars);
        return "report_cars_list";
    }

    @GetMapping("/cars/{car_id}")
    public String carReport(@PathVariable("car_id") long carId, Model model){


        Optional<Car> carFromDb=carRepo.findById(carId);
        if(!carFromDb.isPresent()){
            return "redirect:/error/404";
        }
        Car car=carFromDb.get();

        List<RouteSheet> routeSheets=routeSheetRepo.findByCar(car);
        ArrayList<Waybill> waybills=new ArrayList<>();

        for(RouteSheet routeSheet:routeSheets){
            Waybill waybill=waybillRepo.findByRouteSheet(routeSheet);
            if(waybill!=null){
                waybills.add(waybill);
            }
        }

        ArrayList<CarReportPojo> carReportPojos=new ArrayList<>();

        for(Waybill waybill:waybills){
            CarReportPojo pojo=new CarReportPojo();
            pojo.setNameDelivery(waybill.getNameDelivery());
            pojo.setAddress(waybill.getAddressDelivery());
            pojo.setPhone(waybill.getPhone());
            pojo.setDate(waybill.getRouteSheet().getDate());
            Driver driver=waybill.getRouteSheet().getDriver();
            pojo.setDriver(driver.getName()+" "+driver.getPatronymic()+" "+driver.getSurname());
            Specialist specialist=waybill.getRouteSheet().getSpecialist();
            pojo.setSpecialist(specialist.getName()+" "+specialist.getPatronymic()+" "+specialist.getSurname());
            carReportPojos.add(pojo);

        }
        model.addAttribute("car", car);
        model.addAttribute("carReportPojos", carReportPojos);

        return "car_report";
    }

    @GetMapping("/specialists")
    private String getSpecialists(Model model){

        List<Specialist> specialists=specialistRepo.findAll();
        model.addAttribute("specialists", specialists);
        return "report_specialists_list";
    }

    @GetMapping("/specialists/{specialist_id}")
    private String getSpecialist(@PathVariable("specialist_id") long specialist_id, Model model){

        Optional<Specialist> specialistFromDb=specialistRepo.findById(specialist_id);
        if(!specialistFromDb.isPresent()){
            return "redirect:/error/404";
        }

        Specialist specialist=specialistFromDb.get();
        List<RouteSheet> routeSheets=routeSheetRepo.findBySpecialist(specialist);

        ArrayList<Waybill> waybills=new ArrayList<>();

        for(RouteSheet routeSheet:routeSheets){
            Waybill waybill=waybillRepo.findByRouteSheet(routeSheet);
            if(waybill!=null){
                waybills.add(waybill);
            }
        }

        ArrayList<SpecialistReportPojo> specialistReportPojos=new ArrayList<>();
        for(Waybill waybill:waybills){
            SpecialistReportPojo specialistReportPojo=new SpecialistReportPojo();
            specialistReportPojo.setNameDelivery(waybill.getNameDelivery());
            specialistReportPojo.setAddress(waybill.getAddressDelivery());
            specialistReportPojo.setPhone(waybill.getPhone());
            specialistReportPojo.setDate(waybill.getRouteSheet().getDate());
            RouteSheet routeSheet=waybill.getRouteSheet();
            Car car=routeSheet.getCar();
            specialistReportPojo.setCarName(car.getNumber()+" "+car.getName());
            Driver driver=routeSheet.getDriver();
            specialistReportPojo.setDriver(driver.getName()+" "+driver.getPatronymic()+" "+driver.getSurname());
            specialistReportPojos.add(specialistReportPojo);
        }

        model.addAttribute("specialist", specialist);
        model.addAttribute("specialistReportPojos", specialistReportPojos);
        return "specialist_report";
    }
}
