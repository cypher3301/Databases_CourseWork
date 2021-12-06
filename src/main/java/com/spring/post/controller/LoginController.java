package com.spring.post.controller;


import com.spring.post.entity.Operator;
import com.spring.post.entity.Users;
import com.spring.post.entity.WorkShift;
import com.spring.post.repository.OperatorRepository;
import com.spring.post.repository.WorkShiftRepository;
import com.spring.post.service.AuthenticateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@Controller
@CrossOrigin
@RequestMapping("/")
public class LoginController {

    private final AuthenticateService authenticateService;

    @Autowired
    private WorkShiftRepository workShiftRepository;

    @Autowired
    private OperatorRepository operatorRepository;
    @Autowired
    public LoginController(AuthenticateService authenticateService) {
        this.authenticateService = authenticateService;
    }

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("user", new Users());
        return "login";
    }

    @PostMapping("/login/send")
    public String loginSubmit(@ModelAttribute Users user, Model model) {
        System.out.println(user);
        if (authenticateService.authorize(user)) {
            System.out.println(user);
            final Operator operatorByLogin = operatorRepository.findOperatorByLogin(user.getUsername());
//            System.out.println(operatorByLogin);
            final List<WorkShift> workShifts = workShiftRepository.existsWorkShiftByOperatorAndDatetimeAfter(new Date(2020, 3, 5), operatorByLogin.getId());
//            System.out.println(workShifts);
            StorageController.setUser(user);
            StorageController.setOperator(operatorByLogin);
            if (workShifts.size()==1){
                System.out.println("redirect:/storage");
                return "redirect:/storage";
            }
            EmptyWorkShiftController.setUser(user);
            return "redirect:/storage";
        }
        model.addAttribute("massage", "Login or password is invalid");
        model.addAttribute("isInvalid", true);
        return loginForm(model);
    }
}
