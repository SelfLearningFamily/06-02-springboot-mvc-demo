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

    // initBinder work as a pre-processor for all the requests that are coming
// to this controller. it means this method will be called before every
// method of this controller.
    @InitBinder
    public  void initBinder(WebDataBinder dataBinder){
        // part of the spring APi, use to trim the white spaces around the strings,
        // And the 'true' passing in the constructor indicates if the string only
        // have whitespaces, then trim the string to null

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        // registering the stringTrimmerEditor on databinder to do this to
        //all the strings coming to this controller
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }


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
