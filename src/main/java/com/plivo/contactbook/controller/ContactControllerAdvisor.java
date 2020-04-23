package com.plivo.contactbook.controller;


import com.plivo.contactbook.ContactBookApplication;
import com.plivo.contactbook.model.constant.ContactBookMessageCodes;
import com.plivo.contactbook.model.exception.ContactBookException;
import com.plivo.contactbook.model.response.ApiResponse;
import com.plivo.contactbook.model.response.ErrorMessageResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ContactControllerAdvisor {

    private static final Logger LOGGER= LoggerFactory.getLogger(ContactControllerAdvisor.class);

    @ExceptionHandler(ContactBookException.class)
    @ResponseBody
    public ApiResponse handle(ContactBookException contactBookException) {
    return ApiResponse.buildFail(new ErrorMessageResponse(contactBookException.getContactBookMessageCodes().name(),contactBookException.getContactBookMessageCodes().getMessage()));
   }

   // todo: We can use this exception handle as exit point of all un noticed exception occurred in application
   @ExceptionHandler(Exception.class)
   @ResponseBody
   public ApiResponse handle(Exception e)
   {
      LOGGER.error(e.getLocalizedMessage());
       return ApiResponse.buildFail(new ErrorMessageResponse(ContactBookMessageCodes.CB00.name(),ContactBookMessageCodes.CB00.getMessage()));
   }
}
