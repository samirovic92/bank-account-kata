package com.sg.bankaccountkata.infrasctructure.rest.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ClientDto {

    private String firstName;
    private String lastName;
    private String email;

}
