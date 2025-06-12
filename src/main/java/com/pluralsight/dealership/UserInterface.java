package com.pluralsight.dealership;

import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private static final Scanner scanner = new Scanner(System.in);
    private final VehicleDao vehicleDao;
    private final SalesDao salesDao;
    private final LeaseDao leaseDao;

    public UserInterface(VehicleDao vehicleDao, SalesDao salesDao, LeaseDao leaseDao) {
        this.vehicleDao = vehicleDao;
        this.salesDao = salesDao;
        this.leaseDao = leaseDao;
    }

    public void display() {
        int choice = -1;
        do {
            displayMenu();
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1 -> processGetByPriceRequest();
                case 2 -> processGetByMakeModelRequest();
                case 3 -> processGetByYearRequest();
                case 4 -> processGetByColorRequest();
                case 5 -> processGetByMileageRequest();
                case 6 -> processGetByTypeRequest();
                case 7 -> processGetAllVehiclesRequest();
                case 8 -> addVehicleProcess();
                case 9 -> removeVehicleProcess();
                case 10 -> processSaleOrLease();
                case 99 -> System.out.println("Goodbye!");
                default -> System.out.println("Invalid choice! Please select a valid option.");
            }

        } while (choice != 99);
    }

    private void displayMenu() {
        System.out.println("\n===== Dealership Menu =====");
        System.out.println("1 - Find vehicles within a price range");
        System.out.println("2 - Find vehicles by make / model");
        System.out.println("3 - Find vehicles by year range");
        System.out.println("4 - Find vehicles by color");
        System.out.println("5 - Find vehicles by mileage range");
        System.out.println("6 - Find vehicles by type");
        System.out.println("7 - List ALL vehicles");
        System.out.println("8 - Add a vehicle");
        System.out.println("9 - Remove a vehicle");
        System.out.println("10 - Sell or Lease a vehicle");
        System.out.println("99 - Quit");
        System.out.print("Enter your choice: ");
    }

    private void processGetByPriceRequest() {
        try {
            System.out.print("Enter minimum price: ");
            double minPrice = Double.parseDouble(scanner.nextLine());
            System.out.print("Enter maximum price: ");
            double maxPrice = Double.parseDouble(scanner.nextLine());

            List<Vehicle> vehicles = vehicleDao.getVehiclesByPriceRange(minPrice, maxPrice);
            displayVehicles(vehicles);
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format.");
        }
    }

    private void processGetByMakeModelRequest() {
        System.out.print("Enter make: ");
        String make = scanner.nextLine();
        System.out.print("Enter model: ");
        String model = scanner.nextLine();

        List<Vehicle> vehicles = vehicleDao.getVehiclesByMakeModel(make, model);
        displayVehicles(vehicles);
    }

    private void processGetByYearRequest() {
        try {
            System.out.print("Enter start year: ");
            int start = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter end year: ");
            int end = Integer.parseInt(scanner.nextLine());

            List<Vehicle> vehicles = vehicleDao.getVehiclesByYearRange(start, end);
            displayVehicles(vehicles);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input for year.");
        }
    }

    private void processGetByColorRequest() {
        System.out.print("Enter color: ");
        String color = scanner.nextLine();
        List<Vehicle> vehicles = vehicleDao.getVehiclesByColor(color);
        displayVehicles(vehicles);
    }

    private void processGetByMileageRequest() {
        try {
            System.out.print("Enter minimum mileage: ");
            int min = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter maximum mileage: ");
            int max = Integer.parseInt(scanner.nextLine());

            List<Vehicle> vehicles = vehicleDao.getVehiclesByMileage(min, max);
            displayVehicles(vehicles);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input for mileage.");
        }
    }

    private void processGetByTypeRequest() {
        System.out.print("Enter vehicle type: ");
        String type = scanner.nextLine();

        List<Vehicle> vehicles = vehicleDao.getVehiclesByType(type);
        displayVehicles(vehicles);
    }

    private void processGetAllVehiclesRequest() {
        List<Vehicle> vehicles = vehicleDao.getAllVehicles();
        displayVehicles(vehicles);
    }

    private void displayVehicles(List<Vehicle> vehicles) {
        if (vehicles == null || vehicles.isEmpty()) {
            System.out.println("No vehicles found.");
        } else {
            vehicles.forEach(System.out::println);
        }
    }

    private void addVehicleProcess() {
        try {
            System.out.print("Enter VIN number: ");
            int vin = Integer.parseInt(scanner.nextLine());

            System.out.print("Enter year: ");
            int year = Integer.parseInt(scanner.nextLine());

            System.out.print("Enter make: ");
            String make = scanner.nextLine();

            System.out.print("Enter model: ");
            String model = scanner.nextLine();

            System.out.print("Enter type: ");
            String type = scanner.nextLine();

            System.out.print("Enter color: ");
            String color = scanner.nextLine();

            System.out.print("Enter mileage: ");
            int odometer = Integer.parseInt(scanner.nextLine());

            System.out.print("Enter price: ");
            double price = Double.parseDouble(scanner.nextLine());

            Vehicle vehicle = new Vehicle(vin, year, make, model, type, color, odometer, price);
            vehicleDao.addVehicle(vehicle);
            System.out.println("Vehicle added.");

        } catch (NumberFormatException e) {
            System.out.println("Invalid input.");
        }
    }

    private void removeVehicleProcess() {
        try {
            System.out.print("Enter VIN to remove: ");
            int vin = Integer.parseInt(scanner.nextLine());
            vehicleDao.removeVehicleByVin(vin);
            System.out.println("Remove operation completed.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid VIN input.");
        }
    }

    private void processSaleOrLease() {
        try {
            System.out.print("Enter VIN of vehicle to sell or lease: ");
            int vin = Integer.parseInt(scanner.nextLine());

            Vehicle vehicle = vehicleDao.getVehicleByVin(vin);
            if (vehicle == null) {
                System.out.println("Vehicle not found.");
                return;
            }

            System.out.print("Enter contract date (YYYYMMDD): ");
            String date = scanner.nextLine();

            System.out.print("Enter customer name: ");
            String name = scanner.nextLine();

            System.out.print("Enter customer email: ");
            String email = scanner.nextLine();

            System.out.print("Is this a SALE or LEASE? ");
            String type = scanner.nextLine().toUpperCase();

            if ("SALE".equals(type)) {
                System.out.print("Is it financed? (yes/no): ");
                boolean finance = scanner.nextLine().equalsIgnoreCase("yes");
                SalesContract contract = new SalesContract(date, name, email, vehicle);
                contract.setFinance(finance);
                salesDao.save(contract);
                System.out.println("Sales contract saved to database.");
            } else if ("LEASE".equals(type)) {
                LeaseContract contract = new LeaseContract(date, name, email, vehicle);
                leaseDao.save(contract);
                System.out.println("Lease contract saved to database.");
            } else {
                System.out.println("Invalid contract type.");
                return;
            }

            vehicleDao.removeVehicleByVin(vin);
            System.out.println("Vehicle removed from inventory.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid input.");
        }
    }
}
