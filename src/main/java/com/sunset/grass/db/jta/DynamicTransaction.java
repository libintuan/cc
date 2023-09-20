package com.sunset.grass.db.jta;

import org.mybatis.spring.transaction.SpringManagedTransaction;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DynamicTransaction extends SpringManagedTransaction {
    private DataSource dataSource;

    public DynamicTransaction(DataSource dataSource) {
        super(dataSource);
        this.dataSource = dataSource;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return super.getConnection();
    }
}
