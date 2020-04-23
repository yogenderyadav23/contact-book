package com.plivo.contactbook.dao.mongo;

import com.mongodb.client.result.UpdateResult;
import com.plivo.contactbook.dao.mongo.ContactDetailsRepository;
import com.plivo.contactbook.model.constant.ContactBookMessageCodes;
import com.plivo.contactbook.model.entity.ContactDetails;
import com.plivo.contactbook.model.entity.PaginatedList;
import com.plivo.contactbook.model.exception.ContactBookException;
import lombok.Getter;
import lombok.Setter;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

@Repository
@Getter
@Setter
public class ContactDetailsRepositoryImpl implements ContactDetailsRepository {


    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void createContact(ContactDetails contactDetails) {
          mongoTemplate.save(contactDetails);
    }

    @Override
    public ContactDetails getContactDetailsById(String emailId) {
        Query query=new Query();
        query.addCriteria(Criteria.where("_id").is(emailId));
        ContactDetails contactDetails=mongoTemplate.findOne(query,ContactDetails.class);
        return contactDetails;
    }
    @Override
    public PaginatedList<ContactDetails> getContactDetails(String emailId, String firstName,Long pageNo,Long pageSize) {
        Query query=new Query();
        if(!StringUtils.isEmpty(emailId))
            query.addCriteria(Criteria.where("_id").is(emailId));
        if(!StringUtils.isEmpty(firstName))
            query.addCriteria(Criteria.where("firstName").is(firstName));
        long count=mongoTemplate.count(query,ContactDetails.class);
        if(count<0)
            throw new ContactBookException(ContactBookMessageCodes.CB04);
        query.skip((pageNo-1)*pageSize);
        query.limit(pageSize.intValue());
        List<ContactDetails> listOfContacts=mongoTemplate.find(query,ContactDetails.class);
        Long totalPages=count%pageSize==0?count/pageSize:(count/pageSize)+1;
        PaginatedList<ContactDetails> resultList=new PaginatedList<>(totalPages,pageNo,pageSize,listOfContacts);
        return resultList;
    }
    @Override
    public boolean updateContactDetails(ContactDetails contactDetails) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(contactDetails.getEmailId()));
        Update update=new Update();
        update.set("firstName",contactDetails.getFirstName());
        update.set("lastName",contactDetails.getLastName());
        update.set("primaryNumber",contactDetails.getPrimaryNumber());
        update.set("secondaryNumber",contactDetails.getSecondaryNumber());
        update.set("address",contactDetails.getAddress());
        update.set("updatedAt", DateTime.now());
       UpdateResult updateResult= mongoTemplate.updateFirst(query,update,ContactDetails.class);
      return updateResult.getMatchedCount()>0?Boolean.TRUE:Boolean.FALSE;
   }

    @Override
    public boolean deleteContactDetails(String emailId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(emailId));
      ContactDetails contactDetails= mongoTemplate.findAndRemove(query,ContactDetails.class);
      return (null!=contactDetails);

    }
}
