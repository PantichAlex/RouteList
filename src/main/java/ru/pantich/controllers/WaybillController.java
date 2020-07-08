package ru.pantich.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.pantich.domain.Waybill;
import ru.pantich.formsobjects.WaybillForm;
import ru.pantich.repo.WaybillRepo;
import ru.pantich.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/waybill")
public class WaybillController {

    @Autowired
    private UserService userService;

    @Autowired
    private WaybillRepo waybillRepo;


    @GetMapping
    public String getWaybillList(Model model, HttpServletRequest request){


        String login=request.getUserPrincipal().getName();
        model.addAttribute("isSpecialist", userService.isSpecialist(login));
        model.addAttribute("page_waybill", true);

        Iterable<Waybill> waybills= waybillRepo.findAll();
        model.addAttribute("waybills", waybills);
        return "waybill_list";
    }

    @GetMapping("/add")
    public String getWaybillForm(Model model, HttpServletRequest request){

        model.addAttribute("page_upload", true);
        String login=request.getUserPrincipal().getName();
        model.addAttribute("isSpecialist", userService.isSpecialist(login));
        model.addAttribute("waybill", new WaybillForm());


        return "waybill_add";
    }


    @PostMapping("/add")
    public String createNewWaybill(@ModelAttribute WaybillForm waybillForm, Model model){


        Waybill newWaybill=new Waybill();
        newWaybill.setAddressDelivery(waybillForm.getAddress());
        newWaybill.setNameDelivery(waybillForm.getName());
        newWaybill.setPhone(waybillForm.getPhone());
        waybillRepo.save(newWaybill);
        return "redirect:/waybill";

    }

}
