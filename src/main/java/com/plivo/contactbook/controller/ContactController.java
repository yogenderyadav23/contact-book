package com.plivo.contactbook.controller;


import com.plivo.contactbook.model.request.ContactInfo;
import com.plivo.contactbook.model.response.ApiResponse;
import com.plivo.contactbook.service.IContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value="contact-book/contact")
public class ContactController extends BaseController {

    @Autowired
    private IContactService contactService;

    @RequestMapping(method = RequestMethod.GET)
    public ApiResponse getContact()
    {
        // create a contact here
        return null;
    }



 @RequestMapping(method = RequestMethod.POST)
 public ApiResponse createContact(@RequestBody @Valid ContactInfo contactInfo)
 {
       contactService.createContact(contactInfo);
     return null;
 }


 @RequestMapping(method = RequestMethod.PUT)
 public ApiResponse updateContact(@RequestBody @Valid ContactInfo contactInfo)
 {
        // create a contact here
        return null;
  }


    @RequestMapping(method = RequestMethod.DELETE)
    public ApiResponse deleteContact()
    {
        // create a contact here
        return null;
    }





}
