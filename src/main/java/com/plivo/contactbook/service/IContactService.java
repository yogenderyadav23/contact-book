package com.plivo.contactbook.service;

import com.plivo.contactbook.model.entity.ContactDetails;
import com.plivo.contactbook.model.request.ContactInfo;

public interface IContactService {

    void createContact(ContactInfo contactInfo);

    ContactInfo getContactDetails();

    void deleteContactDetails(String emailId);

    void updateContactDetais(ContactInfo contactInfo);
}
