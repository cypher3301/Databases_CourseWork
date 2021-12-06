package com.spring.post.controller;

import com.spring.post.entity.*;
import com.spring.post.repository.InvoiceRepository;
import com.spring.post.repository.InvoiceTimelineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@CrossOrigin
@RequestMapping("/")
public class StorageController {
    private static Users user;
    private static Operator operator;
    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private InvoiceTimelineRepository invoiceTimelineRepository;

    public static void setUser(Users user) {
        StorageController.user = user;
    }

    public static void setOperator(Operator operator) {
        StorageController.operator = operator;
    }

    @GetMapping("/storage")
    public String storage(Model model) {
        model.addAttribute("user", user);
        final Station station = operator.getStation();
        final long id = station.getId();
        final List<Invoice> collect = invoiceTimelineRepository.findAllByActualStation_Id(id).stream().map(InvoiceTimeline::getInvoice).collect(Collectors.toList());
//        final List<Invoice> current = invoiceRepository.findCurrent(id);
        model.addAttribute("invoices", collect);
        return "packages-on-storage";
    }
}
