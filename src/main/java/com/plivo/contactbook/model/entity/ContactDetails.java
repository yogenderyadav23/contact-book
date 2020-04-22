package com.plivo.contactbook.model.entity;

import com.plivo.contactbook.model.request.ContactInfo;
import lombok.Getter;
import lombok.Setter;
import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "contactDetails")
@Getter
@Setter
public class ContactDetails {
    @Id
    private String emailId;
    @Indexed
    private String firstName;
    private String lastName;
    private String primaryNumber;
    private String secondaryNumber;
    private DateTime createdAt;
    private DateTime updatedAt;
    private Address address;

    public static ContactDetails buildContactDetails(ContactInfo contactInfo)
    {
        ContactDetails contactDetails=new ContactDetails();
        contactDetails.emailId=contactInfo.getEmailId();
        contactDetails.firstName=contactInfo.getFirstName();
        contactDetails.lastName=contactInfo.getLastName();
        contactDetails.primaryNumber=contactInfo.getPrimaryNumber();
        contactDetails.secondaryNumber=contactInfo.getSecondaryNumber();
        contactDetails.createdAt=DateTime.now();
        contactDetails.updatedAt=DateTime.now();
        contactDetails.address=contactInfo.getAddress();
        return contactDetails;
    }
}
