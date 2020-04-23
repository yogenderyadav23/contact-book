package com.plivo.contactbook.controller;


import com.plivo.contactbook.model.request.ContactInfo;
import com.plivo.contactbook.model.response.ApiResponse;
import com.plivo.contactbook.service.IContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "contact-book/contact")
public class ContactController {

    @Autowired
    private IContactService contactService;

    @RequestMapping(method = RequestMethod.GET)
    public ApiResponse getContact(@RequestParam(value = "emailId", required = false) String emailId,
                                  @RequestParam(value = "name", required = false) String firstName,
                                  @RequestParam(value = "pageNo", required = false) Long pageNo) {
        return ApiResponse.buildSuccess(contactService.getContactDetails(emailId, firstName, pageNo));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ApiResponse createContact(@RequestBody @Valid ContactInfo contactInfo) {
        contactService.createContact(contactInfo);
        return ApiResponse.buildSuccess();
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ApiResponse updateContact(@RequestBody @Valid ContactInfo contactInfo) {
        return contactService.updateContactDetais(contactInfo) ? ApiResponse.buildSuccess() : ApiResponse.buildFail();

    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ApiResponse deleteContact(@RequestParam(value = "emailId") String emailId) {
      return   contactService.deleteContactDetails(emailId)? ApiResponse.buildSuccess() : ApiResponse.buildFail();

    }


}
