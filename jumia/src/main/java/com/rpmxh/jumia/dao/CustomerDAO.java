package com.rpmxh.jumia.dao;

import com.rpmxh.jumia.model.Customer;

import java.util.List;

public interface CustomerDAO {

    List<Customer> selectAllCustomers();

    List<Customer> selectCustomersByCountry(String country);

    List<Customer> selectCustomersByState(String state);
}
