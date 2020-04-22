package com.plivo.contactbook;


import com.plivo.contactbook.dao.ContactDetailsRepositoryImpl;
import com.plivo.contactbook.model.entity.Address;
import com.plivo.contactbook.model.entity.ContactDetails;
import com.plivo.contactbook.model.entity.PaginatedList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ContextConfiguration(classes = ContactBookApplication.class)
public class ContactDetailsRepoTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    private ContactDetailsRepositoryImpl contactDetailsRepository;

    private ContactDetails contactDetails;

    @BeforeEach
    public void setUp() throws Exception {
        contactDetailsRepository = new ContactDetailsRepositoryImpl();
        contactDetailsRepository.setMongoTemplate(mongoTemplate);
        contactDetails=getContactDetails();
    }


    public ContactDetails getContactDetails()
    {
        ContactDetails contactDetails = new ContactDetails();
        contactDetails.setEmailId("abc.com");
        contactDetails.setFirstName("yogi");
        contactDetails.setLastName("yadav");
        contactDetails.setPrimaryNumber("472342342");

        Address address = new Address();
        address.setAddressLine("C 21 pinto road");
        address.setCity("New Delhi");
        address.setState("Delhi");
        address.setCountry("India");
        address.setPincode("110078");
        contactDetails.setAddress(address);
        return contactDetails;

    }

    @Test
    public void createContact() {

        contactDetailsRepository.createContact(contactDetails);
        int samplesInCollection = mongoTemplate.findAll(ContactDetails.class).size();
        assertEquals("Only 1 Sample should exist collection, but there are "
                + samplesInCollection, 1, samplesInCollection);
    }

    @Test
    public void updateContact() {
        mongoTemplate.save(contactDetails);
        contactDetails.setPrimaryNumber("748792834");
        contactDetailsRepository.updateContactDetails(contactDetails);
        Query query=new Query();
        query.addCriteria(Criteria.where("_id").is(contactDetails.getEmailId()));
        ContactDetails contactDetailsFind = mongoTemplate.findOne(query,ContactDetails.class);
        assertEquals("previous stored value was 472342342","748792834", contactDetailsFind.getPrimaryNumber());
    }


    @Test
    public void getById() {
        mongoTemplate.save(contactDetails);
        ContactDetails contactDetailsFind = contactDetailsRepository.getContactDetailsById(contactDetails.getEmailId());
        assertEquals("First Name:","yogi", contactDetailsFind.getFirstName());
        assertNull(contactDetailsRepository.getContactDetailsById("xyz@gmail.com"));
    }


    @Test
    public void searchContact()
    {
        mongoTemplate.save(contactDetails);
        PaginatedList<ContactDetails> contactDetailsPaginatedList=contactDetailsRepository.getContactDetails(null,"yogi",1l,1l);
        assertEquals("Size of list:",1, contactDetailsPaginatedList.getList().size());
    }

    @AfterEach
    public void tearDown(){
        mongoTemplate.dropCollection(ContactDetails.class);
    }





}
