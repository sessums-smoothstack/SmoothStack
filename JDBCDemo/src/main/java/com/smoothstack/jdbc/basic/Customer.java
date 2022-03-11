package com.smoothstack.jdbc.basic;

import java.util.Objects;

/**
 CREATE TABLE `Customer` (
 `customerId` int(10) unsigned NOT NULL AUTO_INCREMENT,
 `customerName` varchar(45) NOT NULL,
 `customerSSN` varchar(9) NOT NULL,
 PRIMARY KEY (`customerId`),
 UNIQUE KEY `customerId_UNIQUE` (`customerId`),
 UNIQUE KEY `customerSSN_UNIQUE` (`customerSSN`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
 */

public class Customer {

    private int customerId; // java does not have an unsigned type
    private String customerName;
    private String customerSSN = "555555555";

    public int getCustomerId() {
        return customerId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return customerId == customer.customerId && customerName.equals(customer.customerName) && customerSSN.equals(customer.customerSSN);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", customerName='" + customerName + '\'' +
                ", SSN='" + customerSSN + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, customerName);
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerSSN() {
        return customerSSN;
    }

    public void setSSN(String customerSSN) {
        this.customerSSN = customerSSN;
    }

    public Customer() {
    }

    public Customer(int customerId, String customerName, String customerSSN) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerSSN = customerSSN;
    }


}
