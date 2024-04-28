package com.airbnb.airbin2.service;

import com.airbnb.airbin2.entity.User;
import com.airbnb.airbin2.payload.LoginDto;
import com.airbnb.airbin2.payload.UserDto;

public interface UserService {

    //CRUD OPERATIONS

    //CREATE A USER
    User addUser(UserDto userDto);

    //Verify Login
    String verifyLogin(LoginDto loginDto);
}
