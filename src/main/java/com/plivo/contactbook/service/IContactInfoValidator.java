package com.plivo.contactbook.service;

import com.plivo.contactbook.model.request.ContactInfo;

public interface IContactInfoValidator {

    void validate(ContactInfo contactInfo);
}
