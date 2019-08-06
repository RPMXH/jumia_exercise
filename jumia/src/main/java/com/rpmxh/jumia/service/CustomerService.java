package com.rpmxh.jumia.service;

import com.rpmxh.jumia.dao.CustomerDAO;
import com.rpmxh.jumia.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerDAO customerDAO;

    @Autowired
    public CustomerService(@Qualifier("lite") CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    public List<Customer> selectAllCustomers() {
        return customerDAO.selectAllCustomers();
    }

    public List<Customer> selectCustomersByCountry(String country) {
        return customerDAO.selectCustomersByCountry(country);
    }

    public List<Customer> selectCustomersByState(String state) {
        return customerDAO.selectCustomersByState(state);
    }
}
