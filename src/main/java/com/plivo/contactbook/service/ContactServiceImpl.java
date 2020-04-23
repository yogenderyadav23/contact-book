package com.plivo.contactbook.service;

import com.plivo.contactbook.ContactBookApplication;
import com.plivo.contactbook.dao.mongo.ContactDetailsRepository;
import com.plivo.contactbook.dao.redis.RedisContactDao;
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
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;


@Service
public class ContactServiceImpl implements IContactService {

    private static final Logger LOGGER= LoggerFactory.getLogger(ContactBookApplication.class);

    @Value("${contact.details.page.size:10}")
     private Long pageSize;


     @Autowired
     @Qualifier("contactEmailValidator")
     private IContactInfoValidator contactEmailValidator;

    @Autowired
    @Qualifier("contactNameValidator")
    private IContactInfoValidator contactNameValidator;

     @Autowired
     private ContactDetailsRepository contactDetailsRepository;

     @Autowired
     private RedisContactDao redisContactDao;

    @Override
    public void createContact(ContactInfo contactInfo) {
        try {

            LOGGER.info("Creating contact with emailId:{}",contactInfo.getEmailId());
            contactEmailValidator.validate(contactInfo);
            contactNameValidator.validate(contactInfo);
            ContactDetails contactDetails = ContactDetails.buildContactDetails(contactInfo);
            contactDetailsRepository.createContact(contactDetails);
            LOGGER.info(" Contact created with contactId:{}",contactInfo.getEmailId());
        }
        catch (ContactBookException cb)
        {
            LOGGER.error("Exception in creating contact with contactId:{},code:{},message:{}",contactInfo.getEmailId(),cb.getContactBookMessageCodes().name(),cb.getContactBookMessageCodes().getMessage());
            throw cb;
        }
        catch (Exception e)
        {
           LOGGER.error("Exception in creating contact with contactId:{},Error:{}",contactInfo.getEmailId(),e.getMessage());
           throw e;

        }
      }

    @Override
    public PaginatedList<ContactInfo> getContactDetails(String emailId, String name,Long pageNo) {
         // if  page number. treat it as first page
        if(StringUtils.isEmpty(emailId) && StringUtils.isEmpty(name))
            throw new ContactBookException(ContactBookMessageCodes.CB05);

          if (null == pageNo)
                pageNo = 1l;

        LOGGER.info("Getting search result for emailId:{},firstName:{},pageNo:{}",emailId,name,pageNo);
            // get data from redis
            PaginatedList<ContactInfo> contactInfoPaginatedList;
          /*  contactInfoPaginatedList = redisContactDao.getContactDetailsList(emailId, name, pageNo);
            if (null != contactInfoPaginatedList)
                return contactInfoPaginatedList;*/

      //      LOGGER.info("Result not found in cache getting it from database");
            PaginatedList<ContactDetails> resultList = contactDetailsRepository.getContactDetails(emailId, name, pageNo, pageSize);
            if (resultList.getList().size() == 0)
                throw new ContactBookException(ContactBookMessageCodes.CB04);
            List<ContactInfo> contactInfoList = new ArrayList<>();
            for (ContactDetails contactDetails : resultList.getList()) {
                ContactInfo contactInfo = new ContactInfo();
                contactInfo.setEmailId(contactDetails.getEmailId());
                contactInfo.setFirstName(contactDetails.getFirstName());
                contactInfo.setLastName(contactDetails.getLastName());
                contactInfo.setPrimaryNumber(contactDetails.getPrimaryNumber());
                contactInfo.setSecondaryNumber(contactDetails.getSecondaryNumber());
                contactInfo.setAddress(contactDetails.getAddress());
                contactInfoList.add(contactInfo);
            }
            contactInfoPaginatedList = new PaginatedList<>(resultList.getTotalPages(), resultList.getPageNo(), resultList.getPageSize(), contactInfoList);
         //   redisContactDao.setContactDetailsList(emailId, name, pageNo, contactInfoPaginatedList, 24);
          LOGGER.info("Returning search result for emailId:{},firstName:{},pageNo:{}",emailId,name,pageNo);
            return contactInfoPaginatedList;


    }

    @Override
    public boolean deleteContactDetails(String emailId) {
        LOGGER.info("Deleting contact details for contactId:{}",emailId);
         return contactDetailsRepository.deleteContactDetails(emailId);
    }

    @Override
    public boolean updateContactDetais(ContactInfo contactInfo) {
        LOGGER.info("Updating contact details for contactId:{}",contactInfo.getEmailId());
        ContactDetails contactDetails=ContactDetails.buildContactDetails(contactInfo);
        return contactDetailsRepository.updateContactDetails(contactDetails);
    }
}
