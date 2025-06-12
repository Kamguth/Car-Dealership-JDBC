//package com.pluralsight.dealership;
//
//import java.io.*;
//import java.util.regex.Pattern;
//
//public class DealershipFileManager {
//
//    public Dealership getDealership(String filePath) {
//        Dealership dealership = null;
//
//        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
//            String line = reader.readLine();
//
//            if (line != null) {
//                // Dealership info (1st line) i.e. name address number
//                String[] dealershipParts = line.split(Pattern.quote("|"));
//                dealership = new Dealership(dealershipParts[0], dealershipParts[1], dealershipParts[2]);
//            }
//
//            // Vehicle info
//            while ((line = reader.readLine()) != null) {
//                String[] parts = line.split(Pattern.quote("|"));
//                if (parts.length == 8) {
//                    int vin = Integer.parseInt(parts[0]);
//                    int year = Integer.parseInt(parts[1]);
//                    String make = parts[2];
//                    String model = parts[3];
//                    String type = parts[4];
//                    String color = parts[5];
//                    int odometer = Integer.parseInt(parts[6]);
//                    double price = Double.parseDouble(parts[7]);
//
//                    Vehicle vehicle = new Vehicle(vin, year, make, model, type, color, odometer, price);
//                    dealership.addVehicle(vehicle);
//                }
//            }
//
//        } catch (IOException e) {
//            System.out.println("Error reading file: " + e.getMessage());
//        }
//
//        return dealership;
//    }
//
//    public void saveDealership(Dealership dealership, String filePath) {
//        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
//            writer.write(dealership.getName() + "|" + dealership.getAddress() + "|" + dealership.getPhone());
//            writer.newLine();
//
//            for (Vehicle v : dealership.getAllVehicles()) {
//                writer.write(v.getVin() + "|" + v.getYear() + "|" + v.getMake() + "|" +
//                        v.getModel() + "|" + v.getType() + "|" + v.getColor() + "|" +
//                        v.getOdometer() + "|" + v.getPrice());
//                writer.newLine();
//            }
//        } catch (IOException e) {
//            System.out.println("Error saving dealership: " + e.getMessage());
//        }
//    }
//
//}