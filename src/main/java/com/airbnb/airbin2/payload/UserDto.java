package com.airbnb.airbin2.payload;

import lombok.Data;

@Data
public class UserDto {

    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String mobileNumber;
    private String password;
    private String userRoles;
}
