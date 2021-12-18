package com.spring.post.controller;

import com.spring.post.entity.Operator;
import com.spring.post.entity.Users;
import com.spring.post.repository.ClientRepository;
import com.spring.post.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@CrossOrigin
@RequestMapping("/")
public class CreateInvoiceController {
    private static Users user;
    private static Operator operator;

    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private ClientRepository clientRepository;

    public static void setUser(Users user) {
        CreateInvoiceController.user = user;
    }

    public static void setOperator(Operator operator) {
        CreateInvoiceController.operator = operator;
    }



//    @PostMapping("/invoice/create")
//    public String createInvoice(@ModelAttribute CreateInvoice invoice, Model model) {
//        System.out.println(user);
//        System.out.println(operator);
//        final Client sender = new Client(invoice.getSenderFirstname(),invoice.getSenderPatronymic(), invoice.getSenderPhone(), invoice.getSenderSurname());
//        final Client recipient = new Client(invoice.getRecipientFirstname(),invoice.getRecipientPatronymic(), invoice.getRecipientPhone(), invoice.getRecipientSurname());
//        Invoice invoice2 = Invoice.builder()
//                .id(invoice.hashCode())
//                .datetime(new Date(System.nanoTime()))
//                .deliveryType(InvoiceType.DELIVERY.name())
//                .quantity(invoice.getCount())
//                .type(new PackageType[]{PackageType.OTHER})
//                .clientRecipient(recipient)
//                .clientSender(sender)
//                .operator(operator)
//                .stationRecipient(new Station(new Address(invoice.getRegion(),invoice.getCity()),(short)invoice.getStationNumber()))
//                .build();
//        invoice2.setPackages(new ArrayList<Package>(){{
//            add(new Package(invoice.hashCode(), invoice.getWeight(), invoice.getVolume(), invoice.getInsurance()));
//        }});
//        System.out.println(sender);
//        System.out.println(recipient);
//        System.out.println(invoice);
//        System.out.println(invoice2);
//        invoice2.getPackages().forEach(System.out::println);
//        clientRepository.save(sender);
//        clientRepository.save(recipient);
//        invoiceRepository.save(invoice2);
//        return "redirect:/storage";
//    }
}
