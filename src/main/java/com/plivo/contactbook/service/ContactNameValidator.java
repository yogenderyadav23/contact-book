package com.plivo.contactbook.service;

import com.plivo.contactbook.model.constant.ContactBookMessageCodes;
import com.plivo.contactbook.model.exception.ContactBookException;
import com.plivo.contactbook.model.request.ContactInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.regex.Pattern;

@Service
public class ContactNameValidator implements IContactInfoValidator {

    private static final String NAME_REGEX="^[a-zA-Z\\\\s]*$";


    private Pattern NAME_PATTERN=Pattern.compile(NAME_REGEX);



    @Override
    public void validate(ContactInfo contactInfo) {


        if(!NAME_PATTERN.matcher(contactInfo.getFirstName()).matches())
            throw new ContactBookException(ContactBookMessageCodes.CB02);

        if(!StringUtils.isEmpty(contactInfo.getLastName()) && !NAME_PATTERN.matcher(contactInfo.getLastName()).matches())
            throw new ContactBookException(ContactBookMessageCodes.CB02);


    }
}
