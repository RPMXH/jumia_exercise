package com.rpmxh.jumia.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class CustomerTest {

    @Test
    public void improperPhoneValueStateInvalidTest() {
        Customer c1 = new Customer(1, "halal", "asda");
        assertEquals(c1.getState().toString(), "INVALID");
        assertNull(c1.getCountry());
        assertNull(c1.getCountryCode());
        assertNull(c1.getNumber());
    }

    @Test
    public void unknownPhoneCountryCodeStateInvalidTest() {
        int id = 1;
        String name = "halal";
        String phone = "(51) 691933626";
        Customer c1 = new Customer(id, name, phone);
        assertEquals(c1.getId(), id);
        assertEquals(c1.getName(), name);
        assertEquals(c1.getPhone(), phone);
        assertEquals(c1.getState().toString(), "INVALID");
        assertNull(c1.getCountry());
        assertNull(c1.getCountryCode());
        assertNull(c1.getNumber());
    }

    @Test
    public void excessivePhoneFieldsStateInvalidTest() {
        Customer c1 = new Customer(1, "halal", "(51) (212) 691933626");
        assertEquals(c1.getState().toString(), "INVALID");
        assertNull(c1.getCountry());
        assertNull(c1.getCountryCode());
        assertNull(c1.getNumber());
    }

    @Test
    public void invalidPhoneNumberStateInvalidTest() {
        Customer c1 = new Customer(1, "halal", "(212) asdasd");
        assertEquals(c1.getState().toString(), "INVALID");
        assertNull(c1.getCountry());
        assertNull(c1.getCountryCode());
        assertNull(c1.getNumber());
    }

    @Test
    public void unmatchPhoneFormatInvalidTest() {
        Customer c1 = new Customer(1, "halal", "(212) 1933626");
        assertEquals(c1.getState().toString(), "INVALID");
        assertNull(c1.getCountry());
        assertNull(c1.getCountryCode());
        assertNull(c1.getNumber());
    }

    @Test
    public void matchPhoneMoroccoStateValidTest() {
        Customer c1 = new Customer(1, "halal", "(212) 691933626");
        assertEquals(c1.getState().toString(), "VALID");
        assertEquals(c1.getCountry(), "Morocco");
        assertEquals(c1.getCountryCode(), "+212");
        assertEquals(c1.getNumber(), "691933626");
    }

    @Test
    public void matchPhoneMozambiqueStateValidTest() {
        Customer c1 = new Customer(1, "halal", "(258) 847651504");
        assertEquals(c1.getState().toString(), "VALID");
        assertEquals(c1.getCountry(), "Mozambique");
        assertEquals(c1.getCountryCode(), "+258");
        assertEquals(c1.getNumber(), "847651504");
    }

    @Test
    public void matchPhoneUgandaStateValidTest() {
        Customer c1 = new Customer(1, "halal", "(256) 775069443");
        assertEquals(c1.getState().toString(), "VALID");
        assertEquals(c1.getCountry(), "Uganda");
        assertEquals(c1.getCountryCode(), "+256");
        assertEquals(c1.getNumber(), "775069443");
    }

    @Test
    public void matchPhoneEthiopiaStateValidTest() {
        Customer c1 = new Customer(1, "halal", "(251) 914701723");
        assertEquals(c1.getState().toString(), "VALID");
        assertEquals(c1.getCountry(), "Ethiopia");
        assertEquals(c1.getCountryCode(), "+251");
        assertEquals(c1.getNumber(), "914701723");
    }

    @Test
    public void matchPhoneCameroonStateValidTest() {
        Customer c1 = new Customer(1, "halal", "(237) 697151594");
        assertEquals(c1.getState().toString(), "VALID");
        assertEquals(c1.getCountry(), "Cameroon");
        assertEquals(c1.getCountryCode(), "+237");
        assertEquals(c1.getNumber(), "697151594");
    }
}