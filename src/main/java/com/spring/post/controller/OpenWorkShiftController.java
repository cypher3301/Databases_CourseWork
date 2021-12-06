package com.spring.post.controller;

import com.spring.post.entity.Users;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
@CrossOrigin
public class OpenWorkShiftController {

    private static Users user;



    public static void setUser(Users user) {
        OpenWorkShiftController.user = user;
    }
}
