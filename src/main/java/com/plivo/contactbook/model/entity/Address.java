package com.plivo.contactbook.model.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Address implements Serializable {

    private String addressLine;
    private String city;
    private String state;
    private String country;
    private String pincode;
}
