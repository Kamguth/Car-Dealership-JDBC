
package com.pluralsight.dealership;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DataSourceManager {
    private final BasicDataSource dataSource;

    public DataSourceManager() {
        dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/CarDealershipDatabase");
        dataSource.setUsername("root");
        dataSource.setPassword("kamyg1717");

    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
