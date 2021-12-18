package com.spring.post.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class CreateInvoice {
    private String senderFirstname;
    private String senderSurname;
    private String senderPatronymic;
    private String senderPhone;

    private String recipientFirstname;
    private String recipientSurname;
    private String recipientPatronymic;
    private String recipientPhone;

//    private InvoiceDTO invoice;
    private String region;
    private String city;
    private int stationNumber;

    private String type;
    private double weight;
    private double volume;
    private double insurance;
    private int count;
//    private String description;

    public CreateInvoice(String senderFirstname, String senderSurname, String senderPatronymic, String senderPhone, String recipientFirstname, String recipientSurname, String recipientPatronymic, String recipientPhone, String region, String city, int stationNumber, String type, double weight, double volume, double insurance, int count) {
        this.senderFirstname = senderFirstname;
        this.senderSurname = senderSurname;
        this.senderPatronymic = senderPatronymic;
        this.senderPhone = senderPhone;
        this.recipientFirstname = recipientFirstname;
        this.recipientSurname = recipientSurname;
        this.recipientPatronymic = recipientPatronymic;
        this.recipientPhone = recipientPhone;
        this.region = region;
        this.city = city;
        this.stationNumber = stationNumber;
        this.type = type;
        this.weight = weight;
        this.volume = volume;
        this.insurance = insurance;
        this.count = count;
    }

    public String getSenderFirstname() {
        return senderFirstname;
    }

    public void setSenderFirstname(String senderFirstname) {
        this.senderFirstname = senderFirstname;
    }

    public String getSenderSurname() {
        return senderSurname;
    }

    public void setSenderSurname(String senderSurname) {
        this.senderSurname = senderSurname;
    }

    public String getSenderPatronymic() {
        return senderPatronymic;
    }

    public void setSenderPatronymic(String senderPatronymic) {
        this.senderPatronymic = senderPatronymic;
    }

    public String getSenderPhone() {
        return senderPhone;
    }

    public void setSenderPhone(String senderPhone) {
        this.senderPhone = senderPhone;
    }

    public String getRecipientFirstname() {
        return recipientFirstname;
    }

    public void setRecipientFirstname(String recipientFirstname) {
        this.recipientFirstname = recipientFirstname;
    }

    public String getRecipientSurname() {
        return recipientSurname;
    }

    public void setRecipientSurname(String recipientSurname) {
        this.recipientSurname = recipientSurname;
    }

    public String getRecipientPatronymic() {
        return recipientPatronymic;
    }

    public void setRecipientPatronymic(String recipientPatronymic) {
        this.recipientPatronymic = recipientPatronymic;
    }

    public String getRecipientPhone() {
        return recipientPhone;
    }

    public void setRecipientPhone(String recipientPhone) {
        this.recipientPhone = recipientPhone;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getStationNumber() {
        return stationNumber;
    }

    public void setStationNumber(int stationNumber) {
        this.stationNumber = stationNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public double getInsurance() {
        return insurance;
    }

    public void setInsurance(double insurance) {
        this.insurance = insurance;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreateInvoice)) return false;

        CreateInvoice that = (CreateInvoice) o;

        if (getStationNumber() != that.getStationNumber()) return false;
        if (Double.compare(that.getWeight(), getWeight()) != 0) return false;
        if (Double.compare(that.getVolume(), getVolume()) != 0) return false;
        if (Double.compare(that.getInsurance(), getInsurance()) != 0) return false;
        if (getCount() != that.getCount()) return false;
        if (getSenderFirstname() != null ? !getSenderFirstname().equals(that.getSenderFirstname()) : that.getSenderFirstname() != null)
            return false;
        if (getSenderSurname() != null ? !getSenderSurname().equals(that.getSenderSurname()) : that.getSenderSurname() != null)
            return false;
        if (getSenderPatronymic() != null ? !getSenderPatronymic().equals(that.getSenderPatronymic()) : that.getSenderPatronymic() != null)
            return false;
        if (getSenderPhone() != null ? !getSenderPhone().equals(that.getSenderPhone()) : that.getSenderPhone() != null)
            return false;
        if (getRecipientFirstname() != null ? !getRecipientFirstname().equals(that.getRecipientFirstname()) : that.getRecipientFirstname() != null)
            return false;
        if (getRecipientSurname() != null ? !getRecipientSurname().equals(that.getRecipientSurname()) : that.getRecipientSurname() != null)
            return false;
        if (getRecipientPatronymic() != null ? !getRecipientPatronymic().equals(that.getRecipientPatronymic()) : that.getRecipientPatronymic() != null)
            return false;
        if (getRecipientPhone() != null ? !getRecipientPhone().equals(that.getRecipientPhone()) : that.getRecipientPhone() != null)
            return false;
        if (getRegion() != null ? !getRegion().equals(that.getRegion()) : that.getRegion() != null) return false;
        if (getCity() != null ? !getCity().equals(that.getCity()) : that.getCity() != null) return false;
        return getType() != null ? getType().equals(that.getType()) : that.getType() == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getSenderFirstname() != null ? getSenderFirstname().hashCode() : 0;
        result = 31 * result + (getSenderSurname() != null ? getSenderSurname().hashCode() : 0);
        result = 31 * result + (getSenderPatronymic() != null ? getSenderPatronymic().hashCode() : 0);
        result = 31 * result + (getSenderPhone() != null ? getSenderPhone().hashCode() : 0);
        result = 31 * result + (getRecipientFirstname() != null ? getRecipientFirstname().hashCode() : 0);
        result = 31 * result + (getRecipientSurname() != null ? getRecipientSurname().hashCode() : 0);
        result = 31 * result + (getRecipientPatronymic() != null ? getRecipientPatronymic().hashCode() : 0);
        result = 31 * result + (getRecipientPhone() != null ? getRecipientPhone().hashCode() : 0);
        result = 31 * result + (getRegion() != null ? getRegion().hashCode() : 0);
        result = 31 * result + (getCity() != null ? getCity().hashCode() : 0);
        result = 31 * result + getStationNumber();
        result = 31 * result + (getType() != null ? getType().hashCode() : 0);
        temp = Double.doubleToLongBits(getWeight());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(getVolume());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(getInsurance());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + getCount();
        return result;
    }
}
