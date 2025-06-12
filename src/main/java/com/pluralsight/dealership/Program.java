package com.pluralsight.dealership;

public class Program {
    public static void main(String[] args) {

        // Create DataSourceManager (manages DB connections)
        DataSourceManager dataSourceManager = new DataSourceManager();

        // Create DAOs
        VehicleDao vehicleDao = new VehicleDao(dataSourceManager.getDataSource());
        SalesDao salesDao = new SalesDao(dataSourceManager);
        LeaseDao leaseDao = new LeaseDao(dataSourceManager);

        // Start the user interface with all DAOs
        UserInterface ui = new UserInterface(vehicleDao, salesDao, leaseDao);
        ui.display();

        System.out.println("Program finished. Goodbye!");
    }
}
