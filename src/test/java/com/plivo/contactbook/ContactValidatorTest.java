package com.plivo.contactbook;

import com.plivo.contactbook.model.exception.ContactBookException;
import com.plivo.contactbook.model.request.ContactInfo;
import com.plivo.contactbook.service.ContactEmailValidator;
import com.plivo.contactbook.service.ContactNameValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class ContactValidatorTest {


    private ContactInfo contactInfo;

    private ContactNameValidator contactNameValidator;

    private ContactEmailValidator contactEmailValidator;


    @BeforeEach
    public void setUp()
    {
        contactInfo=new ContactInfo();
        contactInfo.setEmailId("abcgmail.com");
        contactInfo.setFirstName("yogi2");
        contactNameValidator=new ContactNameValidator();
        contactEmailValidator=new ContactEmailValidator();
    }

    @Test
    public void validateName() {
         assertThrows(ContactBookException.class,()->contactNameValidator.validate(contactInfo));
        assertThrows(ContactBookException.class,()->contactEmailValidator.validate(contactInfo));
    }
}
