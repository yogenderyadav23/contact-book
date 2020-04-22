package com.plivo.contactbook.service;

import com.plivo.contactbook.model.entity.PaginatedList;
import com.plivo.contactbook.model.request.ContactInfo;

public interface IContactService {

    void createContact(ContactInfo contactInfo);

    PaginatedList<ContactInfo> getContactDetails(String emailId, String name,Long pageNo);

    void deleteContactDetails(String emailId);

    boolean updateContactDetais(ContactInfo contactInfo);
}
