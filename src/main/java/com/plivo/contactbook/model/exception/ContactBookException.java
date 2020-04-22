package com.plivo.contactbook.model.exception;

import com.plivo.contactbook.model.constant.ContactBookMessageCodes;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactBookException extends  RuntimeException {


  private ContactBookMessageCodes contactBookMessageCodes;

  public ContactBookException(ContactBookMessageCodes contactBookMessageCodes)
  {
      this.contactBookMessageCodes=contactBookMessageCodes;
  }

}
