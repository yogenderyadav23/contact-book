package com.plivo.contactbook.controller;


import com.plivo.contactbook.model.constant.ContactBookMessageCodes;
import com.plivo.contactbook.model.exception.ContactBookException;
import com.plivo.contactbook.model.response.ApiResponse;
import com.plivo.contactbook.model.response.ErrorMessageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ContactControllerAdvisor {


    @ExceptionHandler(ContactBookException.class)
    @ResponseBody
    public ApiResponse handle(ContactBookException contactBookException) {
    return ApiResponse.buildFail(new ErrorMessageResponse(contactBookException.getContactBookMessageCodes().name(),contactBookException.getContactBookMessageCodes().getMessage()));
   }

   @ExceptionHandler(Exception.class)
   @ResponseBody
   public ApiResponse handle(Exception e)
   {
       return ApiResponse.buildFail(new ErrorMessageResponse(ContactBookMessageCodes.CB00.name(),ContactBookMessageCodes.CB00.getMessage()));
   }
}
