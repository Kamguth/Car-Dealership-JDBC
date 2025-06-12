
package com.pluralsight.dealership;

import java.util.List;

public class Program {
    public static void main(String[] args) {


        // Create DataSourceManager (your class to manage DB connections)
        DataSourceManager dataSourceManager = new DataSourceManager();

        // Create the DAO passing your DataSourceManager or DataSource
        VehicleDao vehicleDao = new VehicleDao(dataSourceManager);


        // Start the user interface menu for interactive commands
        UserInterface ui = new UserInterface(vehicleDao);
        ui.display();

        System.out.println("Program finished. Goodbye!");
    }
}
