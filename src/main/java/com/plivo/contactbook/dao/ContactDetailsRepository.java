package com.plivo.contactbook.dao;

import com.plivo.contactbook.model.entity.ContactDetails;
import com.plivo.contactbook.model.entity.PaginatedList;

import java.util.List;



public interface ContactDetailsRepository {


    void createContact(ContactDetails contactDetails);
    ContactDetails getContactDetailsById(String emailId);
    PaginatedList<ContactDetails> getContactDetails(String emailId, String firstName,Long pageNo,Long pageSize);
    boolean updateContactDetails(ContactDetails contactDetails);
    void deleteContactDetails(String id);

}
