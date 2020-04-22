package com.plivo.contactbook.model.constant;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum ContactBookMessageCodes {
    CB00("Something went wrong"),
    CB01("Invalid email pattern"),
    CB02("Invalid name pattern"),
    CB03("Contact already exist"),
    CB04("No record(s) found");
    private String message;
    ContactBookMessageCodes(String message) {
        this.message = message;
    }
}
