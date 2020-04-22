package com.plivo.contactbook.service;

import com.plivo.contactbook.ContactBookApplication;
import com.plivo.contactbook.dao.ContactDetailsRepository;
import com.plivo.contactbook.model.constant.ContactBookMessageCodes;
import com.plivo.contactbook.model.entity.ContactDetails;
import com.plivo.contactbook.model.entity.PaginatedList;
import com.plivo.contactbook.model.exception.ContactBookException;
import com.plivo.contactbook.model.request.ContactInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ContactServiceImpl implements IContactService {

    private static final Logger LOGGER= LoggerFactory.getLogger(ContactBookApplication.class);

    @Value("${contact.details.page.size:1}")
     private Long pageSize;


     @Autowired
     @Qualifier("contactEmailValidator")
     private IContactInfoValidator contactEmailValidator;

    @Autowired
    @Qualifier("contactNameValidator")
    private IContactInfoValidator contactNameValidator;

     @Autowired
     private ContactDetailsRepository contactDetailsRepository;


    @Override
    public void createContact(ContactInfo contactInfo) {
        contactEmailValidator.validate(contactInfo);
        contactNameValidator.validate(contactInfo);
        ContactDetails contactDetails=ContactDetails.buildContactDetails(contactInfo);
        contactDetailsRepository.createContact(contactDetails);
      }

    @Override
    public PaginatedList<ContactInfo> getContactDetails(String emailId, String name,Long pageNo) {
         // if  page number. treat it as first page
         if(null==pageNo)
             pageNo=1l;
        PaginatedList<ContactDetails> result=contactDetailsRepository.getContactDetails(emailId,name,pageNo,pageSize);
        if(result.getList().size()==0)
            throw new ContactBookException(ContactBookMessageCodes.CB04);
        List<ContactInfo> contactInfoList=new ArrayList<>();
        for(ContactDetails contactDetails:result.getList())
        {
            ContactInfo contactInfo=new ContactInfo();
            contactInfo.setEmailId(contactDetails.getEmailId());
            contactInfo.setFirstName(contactDetails.getFirstName());
            contactInfo.setLastName(contactDetails.getLastName());
            contactInfo.setPrimaryNumber(contactDetails.getPrimaryNumber());
            contactInfo.setSecondaryNumber(contactDetails.getSecondaryNumber());
            contactInfo.setAddress(contactDetails.getAddress());
            contactInfoList.add(contactInfo);

        }
        PaginatedList<ContactInfo> contactInfoPaginatedList=new PaginatedList<>(result.getTotalPages(),result.getPageNo(),result.getPageSize(),contactInfoList);
        return contactInfoPaginatedList;
    }

    @Override
    public void deleteContactDetails(String emailId) {
         contactDetailsRepository.deleteContactDetails(emailId);
    }

    @Override
    public boolean updateContactDetais(ContactInfo contactInfo) {
        ContactDetails contactDetails=ContactDetails.buildContactDetails(contactInfo);
        return contactDetailsRepository.updateContactDetails(contactDetails);
    }
}
