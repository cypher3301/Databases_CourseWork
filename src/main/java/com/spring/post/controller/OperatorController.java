package com.spring.post.controller;

import com.spring.post.entity.Operator;
import com.spring.post.service.OperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class OperatorController {


    private final OperatorService operatorService;

    @Autowired
    public OperatorController(OperatorService operatorService) {
        this.operatorService = operatorService;
    }

    @GetMapping("/")
    public String index() {
        return "panel";
    }

    @GetMapping("/operators")
    public String operatorsGet(Model model){
        List<Operator> all = operatorService.findAll();
        model.addAttribute("operators", all);
        return "panel";
    }


}
