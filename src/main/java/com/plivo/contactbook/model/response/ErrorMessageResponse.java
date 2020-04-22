package com.plivo.contactbook.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorMessageResponse {

    public String code;
    public String message;

    public ErrorMessageResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
