package com.rpmxh.jumia.dao;

import com.rpmxh.jumia.datasource.LiteCustomerDatasource;
import com.rpmxh.jumia.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Repository("lite")
public class LiteCustomerDAOImpl implements CustomerDAO {

    private Connection connection = null;
    private Statement statement = null;

    @Autowired
    public LiteCustomerDAOImpl(LiteCustomerDatasource customerDatasource) {
        try {
            Class.forName("org.sqlite.JDBC");
            //this.connection = DriverManager.getConnection("jdbc:sqlite::resource:file:./src/sample.db");
            //this.connection = DriverManager.getConnection("jdbc:sqlite::resource:" + getClass().getResource("./src/sample.db"));
            this.connection = customerDatasource.dataSource().getConnection();
            this.statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Customer> selectAllCustomers() {
        List<Customer> customers = null;
        try {
            ResultSet rs = statement.executeQuery("select id, name, phone from customer");
            customers = new LinkedList<>();
            while (rs.next()) {
                customers.add(
                        new Customer(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("phone")
                        )
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customers;
    }

    @Override
    public List<Customer> selectCustomersByCountry(String country) {
        List<Customer> filteredCustomers =
                selectAllCustomers()
                        .stream()
                        .filter(customer -> country.equalsIgnoreCase(customer.getCountry()))
                        .collect(Collectors.toList());
        return filteredCustomers;
    }

    @Override
    public List<Customer> selectCustomersByState(String state) {
        List<Customer> filteredCustomers =
                selectAllCustomers()
                        .stream()
                        .filter(customer -> state.equalsIgnoreCase(customer.getState().toString()))
                        .collect(Collectors.toList());
        return filteredCustomers;
    }
}
