package com.spring.post.controller;

import com.spring.post.dto.WorkShiftDTO;
import com.spring.post.entity.Users;
import com.spring.post.entity.WorkShift;
import com.spring.post.entity.status.WorkShiftType;
import com.spring.post.repository.OperatorRepository;
import com.spring.post.repository.WorkShiftRepository;
import com.spring.post.entity.Operator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
@CrossOrigin
public class EmptyWorkShiftController {

    private static Users user;
    @Autowired
    private OperatorRepository operatorRepository;

    @Autowired
    private WorkShiftRepository workShiftRepository;

    public static void setUser(Users user) {
        EmptyWorkShiftController.user = user;
    }


    @GetMapping("/empty")
    public String emptyPage(Model model) {
        model.addAttribute("user", user);
        return "empty-shift";
    }

    @GetMapping("/empty/go")
    public String openWorkShift() {
        OpenWorkShiftController.setUser(user);
        return "redirect:/to-open";
    }

    @GetMapping("/exit")
    public String exit() {
        return "login";
    }

    @GetMapping("/to-open")
    public String openWorkShift(Model model) {
        model.addAttribute("user", user);
        return "open-shift";
    }

    @PostMapping("/to-open/open")
    public String registerWorkShift(@ModelAttribute WorkShiftDTO workShiftDTO, Model model) {
        final Operator operator = operatorRepository.findOperatorByLogin(user.getUsername());
        final WorkShift build = WorkShift.builder()
                .type(WorkShiftType.STARTED).
                operator(operator)
                .station(operator.getStation()).build();
        workShiftRepository.save(build);
        model.addAttribute("user", user);

        StorageController.setUser(user);
        StorageController.setOperator(operator);
        return "redirect:/storage";
    }
}
