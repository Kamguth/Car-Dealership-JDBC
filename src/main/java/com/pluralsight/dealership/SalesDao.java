package com.pluralsight.dealership;

import java.sql.Connection;

import java.sql.PreparedStatement;

import java.sql.SQLException;

public class SalesDao {

    private final DataSourceManager dataSource;

    public SalesDao(DataSourceManager dataSource) {

        this.dataSource = dataSource;

    }

    public void save(SalesContract contract) {

        String sql = "INSERT INTO SalesContracts (vin, customer_name, customer_email, sale_date, is_financed, total_price, monthly_payment) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = dataSource.getConnection();

             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, contract.getVehicle().getVin());

            stmt.setString(2, contract.getCustomerName());

            stmt.setString(3, contract.getCustomerEmail());

            stmt.setString(4, contract.getDate());

            stmt.setBoolean(5, contract.isFinance());

            stmt.setDouble(6, contract.getTotalPrice());

            stmt.setDouble(7, contract.getMonthlyPayment());

            stmt.executeUpdate();

        } catch (SQLException e) {

            System.err.println("Error saving sales contract: " + e.getMessage());

        }

    }

}

