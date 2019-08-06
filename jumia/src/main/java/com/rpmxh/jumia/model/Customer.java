package com.rpmxh.jumia.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.lang.NonNull;

public class Customer {

    @NonNull
    private final int id;
    @NonNull
    private final String name;
    @NonNull
    private final String phone;
    @NonNull
    private State state;

    private String country;
    private String countryCode;
    private String number;

    public Customer(@JsonProperty("id") int id, @JsonProperty("name") String name, @JsonProperty("phone") String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        validateContact(phone);
    }

    private void validateContact(String phone) {
        String[] phoneFields = phone.split(" ");
        if (phoneFields.length == 2) {
            String phoneCountryCode = "+" + phoneFields[0].substring(1, phoneFields[0].length() - 1);
            String phoneNumber = phoneFields[1];
            boolean valid = false;
            // IDEALLY this switch case logic would be configured in a database table
            if (phone.matches("\\(237\\) ?[2368]\\d{7,8}$")) {
                valid = true;
                this.country = "Cameroon";
            } else if (phone.matches("\\(251\\) ?[1-59]\\d{8}$")) {
                valid = true;
                this.country = "Ethiopia";
            } else if (phone.matches("\\(212\\) ?[5-9]\\d{8}$")) {
                valid = true;
                this.country = "Morocco";
            } else if (phone.matches("\\(258\\) ?[28]\\d{7,8}$")) {
                valid = true;
                this.country = "Mozambique";
            } else if (phone.matches("\\(256\\) ?\\d{9}$")) {
                valid = true;
                this.country = "Uganda";
            }

            if (valid) {
                this.state = State.VALID;
                this.countryCode = phoneCountryCode;
                this.number = phoneNumber;
                return;
            }
        }
        this.state = State.INVALID;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public State getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getNumber() {
        return number;
    }
}
