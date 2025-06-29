
package com.pluralsight.dealership;

public class Vehicle {
    private int vin;
    private int year;
    private String make;
    private String model;
    private String type;
    private String color;
    private int odometer;
    private double price;

    // Full constructor with all fields
    public Vehicle(int vin, int year, String make, String model, String type, String color, int odometer, double price) {
        this.vin = vin;
        this.year = year;
        this.make = String.valueOf(make);
        this.model = model;
        this.type = String.valueOf(type);
        this.color = color;
        this.odometer = odometer;
        this.price = price;
    }

    public int getVin() {
        return vin;
    }

    public void setVin(int vin) {
        this.vin = vin;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getOdometer() {
        return odometer;
    }

    public void setOdometer(int odometer) {
        this.odometer = odometer;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return vin + " | " + year + " | " + make + " | " + model + " | " + type + " | " + color + " | " + odometer + " | $" + price;
    }
}





//package com.pluralsight;
//
//public class Vehicle {
//    private int vin;
//    private int year;
//    private String make;
//    private String model;
//    private String type;
//    private String color;
//    private int odometer;
//    private double price;
//
//    public Vehicle(String make, String model, int year, String color, int mileage, double price, String type) {
//    }
//
//    //    public Vehicle(int vin, int year, String make, String model, String type, String color, int odometer, double price) {
//    public Vehicle(int id, String make, String model, int year, String color, int odometer, double price, String type)
//    this.vin =vin;
//        this.year =year;
//        this.make =make;
//        this.model =model;
//        this.type =type;
//        this.color =color;
//        this.odometer =odometer;
//        this.price =price;
//}
//
//public int getVin() {
//    return vin;
//}
//
//public void setVin(int vin) {
//    this.vin = vin;
//}
//
//public int getYear() {
//    return year;
//}
//
//public void setYear(int year) {
//    this.year = year;
//}
//
//public String getMake() {
//    return make;
//}
//
//public void setMake(String make) {
//    this.make = make;
//}
//
//public String getModel() {
//    return model;
//}
//
//public void setModel(String model) {
//    this.model = model;
//}
//
//public String getType() {
//    return type;
//}
//
//public void setType(String type) {
//    this.type = type;
//}
//
//public String getColor() {
//    return color;
//}
//
//public void setColor(String color) {
//    this.color = color;
//}
//
//public int getOdometer() {
//    return odometer;
//}
//
//public void setOdometer(int odometer) {
//    this.odometer = odometer;
//}
//
//public double getPrice() {
//    return price;
//}
//
//public void setPrice(double price) {
//    this.price = price;
//}
//
//@Override
//public String toString() {
//    return vin + " | " + year + " | " + make + " | " + model + " | " + type + " | " + color + " | " + odometer + " | $" + price;
//}
//}
