package com.plivo.contactbook.service;

import com.plivo.contactbook.model.request.ContactInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl implements IContactService {


     @Autowired
     private IContactInfoValidator contactInfoValidator;


    @Override
    public void
    createContact(ContactInfo contactInfo) {

          // to validate the email address
          contactInfoValidator.validate(contactInfo);


    }

    @Override
    public ContactInfo getContactDetails() {
        return null;
    }

    @Override
    public void deleteContactDetails(String emailId) {

    }

    @Override
    public void updateContactDetais(ContactInfo contactInfo) {

    }
}
