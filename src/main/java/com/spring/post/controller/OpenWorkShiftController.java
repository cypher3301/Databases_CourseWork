package com.spring.post.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class OpenWorkShiftController {

    @GetMapping("/to-open")
    public String openWorkShift() {
        return "open-shift";
    }
}
