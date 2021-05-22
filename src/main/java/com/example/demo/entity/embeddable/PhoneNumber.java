package com.example.demo.entity.embeddable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.xml.bind.ValidationException;
import java.util.Objects;

@Embeddable
public class PhoneNumber {

    protected String phone;

    public PhoneNumber(String phone) throws ValidationException {
        this.phone = convertNumber(phone);

    }

    public PhoneNumber() {
    }

    private static String convertNumber(String number) throws ValidationException {
        String result = number;
        if (result.contains("+"))
            result = result.replace("+", "");
//        if (result.matches("[-+]?\\d+"))
//            if (result.length() == 12)
//                return result.substring(2);
//        if (result.length()==10)
//            return result;
//        throw new ValidationException("Not correct phone number");
        return result;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) throws ValidationException {
        this.phone = convertNumber(phone);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhoneNumber that = (PhoneNumber) o;
        return Objects.equals(phone, that.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phone);
    }

    @Override
    public String toString() {
        return "phone='" + phone + '\'';
    }
}
