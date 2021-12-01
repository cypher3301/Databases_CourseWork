package com.spring.post.controller;


import com.spring.post.entity.Users;
import com.spring.post.service.AuthenticateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
@RequestMapping("/")
public class LoginController {

    private final AuthenticateService authenticateService;

    @Autowired
    public LoginController(AuthenticateService authenticateService) {
        this.authenticateService = authenticateService;
    }

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("user", new Users());
        return "login";
    }

    @PostMapping("/login")
    public String loginSubmit(@ModelAttribute Users user, Model model) {
        if (authenticateService.authorize(user)) {
            EmptyWorkShiftController.setUser(user);
            return "redirect:/empty";
        }
        model.addAttribute("massage", "Login or password is invalid");
        model.addAttribute("isInvalid", true);
        return loginForm(model);
    }
}
