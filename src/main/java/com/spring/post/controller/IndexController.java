package com.spring.post.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@CrossOrigin
public class IndexController {


//    private final OperatorService operatorService;
//
//    @Autowired
//    public OperatorController(OperatorService operatorService) {
//        this.operatorService = operatorService;
//    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

//    @GetMapping("/operators")
//    public String operatorsGet(Model model){
//        List<Operator> all = operatorService.findAll();
//        model.addAttribute("operators", all);
//        return "index";
//    }


}
