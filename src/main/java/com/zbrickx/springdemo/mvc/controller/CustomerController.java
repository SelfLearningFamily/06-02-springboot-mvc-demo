package com.zbrickx.springdemo.mvc.controller;

import com.zbrickx.springdemo.mvc.model.Customer;
import jakarta.validation.Valid;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class CustomerController {

    @GetMapping("/")
    public String showForm(Model model){
        Customer customer  = new Customer();
        model.addAttribute("customer", customer);

        return "customer-form";
    }

    @PostMapping("/processform")
    public String landingFormProcess(
            @Valid @ModelAttribute("customer") Customer customer,
            BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "customer-form";
        else
            return "customer-confirmation";
    }
}
