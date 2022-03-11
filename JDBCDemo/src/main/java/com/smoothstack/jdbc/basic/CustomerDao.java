package com.smoothstack.jdbc.basic;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDao {

    public List<Customer> getCustomers(String tableName) throws SQLException {
        List<Customer> customers = null;
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/store1","sam","3301");

            Statement stmt = conn.createStatement();
            ResultSet rset = stmt.executeQuery("select customerId, customerName, customerSSN from " + tableName + ";");
            while (rset.next()) {
                if (customers == null) customers = new ArrayList<>();
                Integer customerId = rset.getInt(1);
                String customerName = rset.getString(2);
                String customerSSN = rset.getString(3);
                Customer customer = new Customer(customerId, customerName, customerSSN);
                customers.add(customer);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) conn.close();
        }
        return customers;
    }
}
