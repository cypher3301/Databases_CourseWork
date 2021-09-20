package com.spring.post.controller;

import com.spring.post.service.WorkShiftService;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/api/work-shift")
public class WorkShiftController {

    private WorkShiftService workShiftService;

    @GetMapping("/is-work-shift-opened")
    public String isWorkShiftOpened(@RequestParam String date) {

        try {
            if (workShiftService.isWorkShiftOpenedOnSpecialDay(date)){
                return "true";
            }
        } catch (ParseException e) {
            return "String is not pasable to Date";
        }
        return "false";
    }

}
