package com.spring.post.generate;

import com.spring.post.entity.Invoice;
import com.spring.post.entity.Operator;
import com.spring.post.entity.Station;
import com.spring.post.entity.Waybill;
import com.spring.post.entity.status.WaybillType;

import java.util.List;
import java.util.Random;

public class GenerateWayBill extends Generator {

    public static Waybill getNewWeyBill() {
        Waybill waybill = new Waybill();
        Station senderStation = GenerateStation.getNewStation();
        Station recipientStation = GenerateStation.getNewStation();
        GenerateWayBill generateWayBill = new GenerateWayBill();
        waybill.setId(generateWayBill.generateId());
        waybill.setDateAndTime(generateWayBill.generateDatetime());
        waybill.setType(generateWayBill.generateWaybillType());
        waybill.setQuantity(new Random().nextInt() % 255);
        waybill.setDriver(GenerateDriver.getNewDriver());
        waybill.setOperator(GenerateOperator.getNewOperator(senderStation));
        waybill.setStationSender(senderStation);
        waybill.setStationRecipient(recipientStation);
        waybill.setInvoices(GenerateInvoice.getListInvoices((List<Invoice>) waybill.getInvoices()));

        return waybill;
    }

    public static Waybill getNewWeyBill(Operator operator) {
        Waybill waybill = new Waybill();
        Station senderStation = GenerateStation.getNewStation();
        Station recipientStation = GenerateStation.getNewStation();
        GenerateWayBill generateWayBill = new GenerateWayBill();
        waybill.setId(generateWayBill.generateId());
        waybill.setDateAndTime(generateWayBill.generateDatetime());
        waybill.setType(generateWayBill.generateWaybillType());
        waybill.setQuantity(new Random().nextInt() % 255);
        waybill.setDriver(GenerateDriver.getNewDriver());
        waybill.setOperator(GenerateOperator.getNewOperator(senderStation));
        waybill.setStationSender(senderStation);
        waybill.setStationRecipient(recipientStation);
        waybill.setInvoices(GenerateInvoice.getListInvoices((List<Invoice>) waybill.getInvoices(), operator));

        return waybill;
    }

    private WaybillType generateWaybillType() {
        if (new Random().nextInt() % 2 == 0) {
            return WaybillType.COMING;
        }
        return WaybillType.LEAVING;
    }

}
