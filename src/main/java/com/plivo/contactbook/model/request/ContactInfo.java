package com.plivo.contactbook.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.plivo.contactbook.model.entity.Address;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class ContactInfo {

    @JsonProperty("email_id")
    @NotNull
    @Getter
    @Setter
    private String emailId;
    @NotNull
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    @NotNull
    @JsonProperty("primary_num")
    private String primaryNumber;
    @JsonProperty("secondary_num")
    private String secondaryNumber;
    @JsonProperty("address")
    @NotNull
    private Address address;



}

