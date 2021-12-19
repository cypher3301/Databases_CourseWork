package com.spring.post.controller;

import com.spring.post.dto.CreateInvoice;
import com.spring.post.entity.*;
import com.spring.post.entity.Package;
import com.spring.post.entity.embeddable.Address;
import com.spring.post.entity.status.InvoiceType;
import com.spring.post.entity.status.PackageType;
import com.spring.post.entity.status.TimelineStatus;
import com.spring.post.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@CrossOrigin
@RequestMapping("/")
public class StorageController {
    private static Users user;
    private static Operator operator;

    @Autowired
    private InvoiceTimelineRepository invoiceTimelineRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private PackageRepository packageRepository;
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private StationRepository stationRepository;






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
        model.addAttribute("invoices", collect);
        return "packages-on-storage";
    }

    @GetMapping("/invoice/form")
    public String getForm(Model model) {
        model.addAttribute("invoice", new CreateInvoice());
        model.addAttribute("user", user);
        return "create-invoice";
    }



    @PostMapping("/invoice/create")
    public String createInvoice(@ModelAttribute CreateInvoice invoice, Model model) {
        System.out.println(user);
        System.out.println(operator);
        final Client sender = new Client(invoice.getSenderFirstname(),invoice.getSenderPatronymic(), invoice.getSenderPhone(), invoice.getSenderSurname());
        final Client recipient = new Client(invoice.getRecipientFirstname(),invoice.getRecipientPatronymic(), invoice.getRecipientPhone(), invoice.getRecipientSurname());
        Station station = new Station(new Address(invoice.getRegion(), invoice.getCity(), String.valueOf(invoice.getStationNumber())), (short) invoice.getStationNumber());
        final Station byAddressAndNumber = stationRepository.findByAddressAndNumber(new Address(invoice.getRegion(), invoice.getCity(), String.valueOf(invoice.getStationNumber())), (short) invoice.getStationNumber());
        if (byAddressAndNumber != null) {
            station = byAddressAndNumber;
        }
        Invoice invoice2 = Invoice.builder()
//                .id(invoice.hashCode())
                .datetime(new Date(System.nanoTime()))
                .deliveryType(InvoiceType.DELIVERY.name())
                .quantity(invoice.getCount())
                .type(new PackageType[]{PackageType.OTHER})
                .clientRecipient(recipient)
                .clientSender(sender)
                .operator(operator)
                .stationRecipient(station)
                .build();
        final Package aPackage = new Package(invoice.getWeight(), invoice.getVolume(), invoice.getInsurance());
        invoice2.setPackages(new ArrayList<Package>(){{
            add(aPackage);
        }});
//        operator.getInvoices().add(invoice2);
        aPackage.setInvoice(invoice2);
        InvoiceTimeline invoiceTimeline = new InvoiceTimeline(TimelineStatus.RECEIVED, operator.getStation());
        invoiceTimeline.setInvoice(invoice2);
        invoiceTimeline.setDatetime(new Date(System.currentTimeMillis()));
        invoice2.setTimeline(new ArrayList<InvoiceTimeline>(){{add(invoiceTimeline);}});

        stationRepository.save(station);
        clientRepository.save(sender);
        clientRepository.save(recipient);
        invoiceRepository.save(invoice2);
        packageRepository.saveAll(invoice2.getPackages());
        invoiceTimelineRepository.save(invoiceTimeline);
        return "redirect:/storage";
    }
}
