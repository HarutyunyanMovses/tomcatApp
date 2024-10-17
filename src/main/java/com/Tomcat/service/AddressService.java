package com.Tomcat.service;

import com.Tomcat.model.enttis.Address;
import com.Tomcat.repository.addressRepositoryImpl.AddressRepositoryJPA;

public class AddressService {
    private static final AddressRepositoryJPA addressRepositoryJPA = new AddressRepositoryJPA();

    public static void addAddress(Address address) {
        addressRepositoryJPA.addAddress(address);
    }

}
