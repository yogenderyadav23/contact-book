package com.plivo.contactbook.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class ApiResponse {

    private boolean success;
    private Object data;

    private ApiResponse(boolean success, Object data) {
        this.success = success;
        this.data = data;
    }

    private ApiResponse(boolean success) {
        this.success = success;
    }

    public static ApiResponse buildSuccess(Object data) {
        return new ApiResponse(Boolean.TRUE, data);
    }

    public static ApiResponse buildSuccess()
    {
        return new ApiResponse(Boolean.TRUE);
    }

    public static ApiResponse buildFail()
    {
        return new ApiResponse(Boolean.FALSE);
    }

    public static ApiResponse buildFail(Object data) {
        return new ApiResponse(Boolean.FALSE, data);
    }


}

