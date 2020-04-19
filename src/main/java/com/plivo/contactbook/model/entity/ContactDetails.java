package com.plivo.contactbook.model.entity;

import org.joda.time.DateTime;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "contactDetails")
public class ContactDetails {

    private String emailId;
    private String firstName;
    private String lastName;
    private String primaryNumber;
    private String secondaryNumber;
    private DateTime createdAt;
    private DateTime updatedAt;
    private Address address;
}
