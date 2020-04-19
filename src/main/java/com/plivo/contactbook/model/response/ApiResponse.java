package com.plivo.contactbook.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse {

    private boolean success;
    private Object data;

    public ApiResponse(boolean success, Object data) {
        this.success = success;
        this.data = data;
    }

    public static ApiResponse buildSuccess(Object data) {
        return new ApiResponse(Boolean.TRUE, data);
    }

    public static ApiResponse buildFail(Object data) {
        return new ApiResponse(Boolean.FALSE, data);
    }


}
