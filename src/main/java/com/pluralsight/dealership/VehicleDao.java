
package com.pluralsight.dealership;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;


public class VehicleDao {
    private final DataSource dataSource;

    public VehicleDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // Maps a single row in the result set to a Vehicle object
    private Vehicle mapRowToVehicle(ResultSet rs) throws SQLException {
        int vin = rs.getInt("vin");
        String make = rs.getString("make");
        String model = rs.getString("model");
        int year = rs.getInt("year");
        String color = rs.getString("color");
        int odometer = rs.getInt("mileage");
        double price = rs.getDouble("price");
        String type = rs.getString("type");

        return new Vehicle(vin, year, make, model, type, color, odometer, price);
    }

    // Helper method to execute queries with parameter setting
    private List<Vehicle> queryVehicles(String sql, SQLConsumer<PreparedStatement> parameterSetter) {
        List<Vehicle> vehicles = new ArrayList<>();

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            parameterSetter.accept(stmt);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                vehicles.add(mapRowToVehicle(rs));
            }

        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }

        return vehicles;
    }

    // Functional interface for prepared statement customization
    @FunctionalInterface
    private interface SQLConsumer<T> {
        void accept(T t) throws SQLException;
    }


    public List<Vehicle> getVehiclesByPriceRange(double min, double max) {
        String sql = "SELECT * FROM vehicles WHERE price BETWEEN ? AND ?";
        return queryVehicles(sql, ps -> {
            ps.setDouble(1, min);
            ps.setDouble(2, max);
        });
    }
    public Vehicle getVehicleByVin(int vin) {
        String sql = "SELECT * FROM vehicles WHERE vin = ?";
        List<Vehicle> vehicles = queryVehicles(sql, ps -> ps.setInt(1, vin));
        if (vehicles.isEmpty()) {
            return null;
        } else {
            return vehicles.get(0);
        }
    }


    public List<Vehicle> getVehiclesByMakeModel(String make, String model) {
        String sql = "SELECT * FROM vehicles WHERE make = ? AND model = ?";
        return queryVehicles(sql, ps -> {
            ps.setString(1, make);
            ps.setString(2, model);
        });
    }

    public List<Vehicle> getVehiclesByYearRange(int min, int max) {
        String sql = "SELECT * FROM vehicles WHERE year BETWEEN ? AND ?";
        return queryVehicles(sql, ps -> {
            ps.setInt(1, min);
            ps.setInt(2, max);
        });
    }

    public List<Vehicle> getVehiclesByColor(String color) {
        String sql = "SELECT * FROM vehicles WHERE color = ?";
        return queryVehicles(sql, ps -> ps.setString(1, color));
    }

    public List<Vehicle> getVehiclesByMileageRange(int min, int max) {
        String sql = "SELECT * FROM vehicles WHERE mileage BETWEEN ? AND ?";
        return queryVehicles(sql, ps -> {
            ps.setInt(1, min);
            ps.setInt(2, max);
        });
    }

    // Alias for compatibility if another part of the app expects this name
    public List<Vehicle> getVehiclesByMileage(int min, int max) {
        return getVehiclesByMileageRange(min, max);
    }

    public List<Vehicle> getVehiclesByType(String type) {
        String sql = "SELECT * FROM vehicles WHERE type = ?";
        return queryVehicles(sql, ps -> ps.setString(1, type));
    }

    public List<Vehicle> getAllVehicles() {
        String sql = "SELECT * FROM vehicles";
        return queryVehicles(sql, ps -> {});
    }

    public void addVehicle(Vehicle vehicle) {
        String sql = "INSERT INTO vehicles (vin, make, model, year, color, mileage, price, type) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, vehicle.getVin());
            stmt.setString(2, vehicle.getMake());
            stmt.setString(3, vehicle.getModel());
            stmt.setInt(4, vehicle.getYear());
            stmt.setString(5, vehicle.getColor());
            stmt.setInt(6, vehicle.getOdometer());
            stmt.setDouble(7, vehicle.getPrice());
            stmt.setString(8, vehicle.getType());

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error inserting vehicle: " + e.getMessage());
        }
    }

    // New method to remove a vehicle by its VIN
    public void removeVehicleByVin(int vin) {
        String deleteInventorySql = "DELETE FROM Inventory WHERE VIN = ?";
        String deleteVehicleSql = "DELETE FROM Vehicles WHERE VIN = ?";

        try (Connection conn = dataSource.getConnection()) {
            conn.setAutoCommit(false);

            try (
                    PreparedStatement stmt1 = conn.prepareStatement(deleteInventorySql);
                    PreparedStatement stmt2 = conn.prepareStatement(deleteVehicleSql)
            ) {
                stmt1.setInt(1, vin);
                stmt1.executeUpdate();

                stmt2.setInt(1, vin);
                stmt2.executeUpdate();

                conn.commit();
                System.out.println("Vehicle with VIN " + vin + " was removed successfully.");
            } catch (SQLException e) {
                conn.rollback();
                System.err.println("Error during vehicle delete: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }

}
