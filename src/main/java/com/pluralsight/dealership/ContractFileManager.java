
//package com.pluralsight.dealership;
//
//import java.io.BufferedWriter;
//import java.io.FileWriter;
//import java.io.IOException;
//
//public class ContractFileManager {
//    private final String filePath = "src/main/resources/contracts.csv";
//
//    public void saveContract(Contract contract) {
//        try (FileWriter writer = new FileWriter(filePath, true)) {
//            if (contract instanceof SalesContract) {
//                SalesContract sc = (SalesContract) contract;
//                writer.append("SALE").append("|")
//                        .append(sc.getDate()).append("|")
//                        .append(sc.getCustomerName()).append("|")
//                        .append(sc.getCustomerEmail()).append("|")
//                        .append(vehicleDetails(sc)).append("|")
//                        .append(String.format("%.2f|%.2f|%.2f|%.2f|%s|%.2f",
//                                sc.getSalesTax(),
//                                sc.getRecordingFee(),
//                                sc.getProcessingFee(),
//                                sc.getTotalPrice(),
//                                sc.isFinance() ? "YES" : "NO",
//                                sc.getMonthlyPayment()))
//                        .append("\n");
//            } else if (contract instanceof LeaseContract) {
//                LeaseContract lc = (LeaseContract) contract;
//                writer.append("LEASE").append("|")
//                        .append(lc.getDate()).append("|")
//                        .append(lc.getCustomerName()).append("|")
//                        .append(lc.getCustomerEmail()).append("|")
//                        .append(vehicleDetails(lc)).append("|")
//                        .append(String.format("%.2f|%.2f|%.2f|%.2f",
//                                lc.getExpectedEndingValue(),
//                                lc.getLeaseFee(),
//                                lc.getTotalPrice(),
//                                lc.getMonthlyPayment()))
//                        .append("\n");
//            }
//        } catch (IOException e) {
//            System.out.println("Failed to save contract: " + e.getMessage());
//        }
//    }
//
//    private String vehicleDetails(Contract contract) {
//        Vehicle v = contract.getVehicleSold();
//        return String.format("%d|%d|%s|%s|%s|%s|%.2f",
//                v.getVin(),
//                v.getYear(),
//                v.getMake(),
//                v.getModel(),
//                v.getColor(),
//                v.getType(),
//                v.getPrice());
//
//    }
//
//
//}
