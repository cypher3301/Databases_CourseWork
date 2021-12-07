package com.spring.post.controller;

import com.spring.post.entity.Client;
import com.spring.post.entity.Invoice;
import com.spring.post.entity.Package;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
@RequestMapping("/invoice")
public class CreateInvoiceController {

    @GetMapping("/form")
    public String getForm(Model model) {
        model.addAttribute("package", new Package());
        model.addAttribute("invoice", new Invoice());
        model.addAttribute("clientRecipient", new Client());
        model.addAttribute("clientSender", new Client());
        return "create-invoice";
    }

    @PostMapping
    public String createInvoice(@ModelAttribute Client sender, @ModelAttribute Client recipient, @ModelAttribute Invoice invoice, @ModelAttribute Package pack, Model model) {


        return "redirect:/storage";
    }
}
