package com.spring.post.controller;

import com.spring.post.entity.Users;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/empty")
@CrossOrigin
public class EmptyWorkShiftController {

    private static Users user;

    public static void setUser(Users user) {
        EmptyWorkShiftController.user = user;
    }

    @GetMapping
    public String emptyPage(Model model) {
        model.addAttribute("user", user);
        return "empty-shift";
    }

    @GetMapping("/exit")
    public String exit() {
        return "login";
    }
}
