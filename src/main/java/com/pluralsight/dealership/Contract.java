
package com.pluralsight.dealership;

public abstract class Contract {
    protected String date;
    protected String customerName;
    protected String customerEmail;
    protected Vehicle vehicleSold;

    public Contract(String date, String customerName, String customerEmail, Vehicle vehicleSold) {
        this.date = date;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.vehicleSold = vehicleSold;
    }

    public String getDate() {
        return date;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public void setVehicleSold(Vehicle vehicleSold) {
        this.vehicleSold = vehicleSold;
    }

    public Vehicle getVehicleSold() {
        return vehicleSold;

    }

    public Vehicle getVehicle() {
        return vehicleSold;
    }

    public abstract double getTotalPrice();
    public abstract double getMonthlyPayment();
}
