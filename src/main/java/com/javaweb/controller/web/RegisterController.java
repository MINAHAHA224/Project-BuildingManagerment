package com.javaweb.controller.web;

import com.javaweb.model.dto.RegisterDTO;
import com.javaweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class RegisterController {

    @Autowired
    private UserService userService;



    @GetMapping("/register")
    public ModelAndView postRegisterPage(@Valid @ModelAttribute("registerUser") RegisterDTO registerDTO, BindingResult registerBingdingResult) {
        ModelAndView mav = new ModelAndView("register");
        mav.addObject("registerUser" , new RegisterDTO());
        List<FieldError> errors = registerBingdingResult.getFieldErrors();
        for (FieldError error : errors) {
            System.out.println(">>>>" + error.getField() + " - " + error.getDefaultMessage());
        }

        if (registerBingdingResult.hasErrors()) {
            return new ModelAndView("register");
        }
        this.userService.handleSaveRegister(registerDTO);

        return mav;
    }
}
