package com.sg.bankaccountkata.domaine;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Client {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}
