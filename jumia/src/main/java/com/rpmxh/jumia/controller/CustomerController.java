package com.rpmxh.jumia.controller;

import com.rpmxh.jumia.model.Customer;
import com.rpmxh.jumia.model.State;
import com.rpmxh.jumia.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("customers")
public class CustomerController {

    private final CustomerService customerService;
    private final String pageTop = "<!DOCTYPE html><html lang=\"en\"><head>" +
            "<meta charset=\"UTF-8\"><meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">" +
            "<meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\"><title>Phone Validator</title>" +
            "</head><body><table><tr><th>ID</td><th>Name</th>" +
            "<th>Phone Number</th><th>State</th><th>Country</th>" +
            "<th>Country Code</th><th>Number</th></tr>";
    private final String pageEnd = "</table></body></html>";

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<Customer> selectAllCustomers() {
        return customerService.selectAllCustomers();
    }

    @GetMapping(value = "/country={cty}")
    public List<Customer> selectCustomersByCountry(@PathVariable("cty") String country) {
        return customerService.selectCustomersByCountry(country);
    }

    @GetMapping(value = "/state={state}")
    public List<Customer> selectCustomersByState(@PathVariable("state") String state) {
        return customerService.selectCustomersByState(state);
    }

    @GetMapping(value = "/p")
    public String allCustomersPage() {
        StringBuilder body = new StringBuilder();
        selectAllCustomers().forEach(
                customer -> body.append(customerTableDetail(customer))
        );
        return pageTop + body.toString() + pageEnd;
    }

    @GetMapping(value = "/p/country={cty}")
    public String countryCustomersPage(@PathVariable("cty") String country) {
        StringBuilder body = new StringBuilder();
        selectCustomersByCountry(country).forEach(
                customer -> body.append(customerTableDetail(customer))
        );
        return pageTop + body.toString() + pageEnd;
    }

    @GetMapping(value = "/p/state={state}")
    public String stateCustomersPage(@PathVariable("state") String state) {
        StringBuilder body = new StringBuilder();
        selectCustomersByState(state).forEach(
                customer -> body.append(customerTableDetail(customer))
        );
        return pageTop + body.toString() + pageEnd;
    }

    private String customerTableDetail(Customer customer) {
        StringBuilder detail = new StringBuilder();

        detail.append("<tr>");
        detail.append("<td>").append(customer.getId()).append("</td>");
        detail.append("<td>").append(customer.getName()).append("</td>");
        detail.append("<td>").append(customer.getPhone()).append("</td>");
        detail.append("<td>").append(customer.getState()).append("</td>");
        if (customer.getState() == State.VALID) {
            detail.append("<td>").append(customer.getCountry()).append("</td>");
            detail.append("<td>").append(customer.getCountryCode()).append("</td>");
            detail.append("<td>").append(customer.getNumber()).append("</td>");
        } else {
            detail.append("<td>").append("</td>");
            detail.append("<td>").append("</td>");
            detail.append("<td>").append("</td>");
        }
        detail.append("</tr>");

        return detail.toString();
    }
}
