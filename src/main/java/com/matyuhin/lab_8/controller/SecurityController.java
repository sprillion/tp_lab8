package com.matyuhin.lab_8.controller;

import com.matyuhin.lab_8.dto.UserDto;
import com.matyuhin.lab_8.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class SecurityController {

    private UserService userService;

    public SecurityController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/index")
    public String home(){
        return "index";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        var user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto userDto,
                               BindingResult bindingResult,
                               Model model){
        var existingUser = userService.findUserByEmail(userDto.getEmail());

        if (existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()){
            bindingResult.rejectValue("email", null,
                    "Электронная почта уже используется");
        }

        if (bindingResult.hasErrors()){
            model.addAttribute("user", userDto);
            return "/register";
        }

        userService.saveUser(userDto);
        return "redirect:/register?success";
    }

    @GetMapping("/users")
    public String users(Model model){
        var users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "users";
    }
}
