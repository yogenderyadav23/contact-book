package com.plivo.contactbook.model.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Address {

    private String addressLine;
    private String city;
    private String state;
    private String country;
    private String pincode;
}
