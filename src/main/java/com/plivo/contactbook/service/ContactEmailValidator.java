package com.plivo.contactbook.service;

import com.plivo.contactbook.dao.mongo.ContactDetailsRepository;
import com.plivo.contactbook.model.constant.ContactBookMessageCodes;
import com.plivo.contactbook.model.exception.ContactBookException;
import com.plivo.contactbook.model.request.ContactInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class ContactEmailValidator implements IContactInfoValidator {


    private static final String EMAIL_PATTERN= "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    @Autowired
    private ContactDetailsRepository contactDetailsRepository;

    private Pattern pattern=Pattern.compile(EMAIL_PATTERN);

    @Override
    public void validate(ContactInfo contactInfo) {

        if(!pattern.matcher(contactInfo.getEmailId()).matches())
            throw new ContactBookException(ContactBookMessageCodes.CB01);


        if(null!=contactDetailsRepository.getContactDetailsById(contactInfo.getEmailId()))
            throw new ContactBookException(ContactBookMessageCodes.CB03);

   }
}
