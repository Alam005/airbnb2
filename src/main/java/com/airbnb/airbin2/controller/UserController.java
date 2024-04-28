package com.airbnb.airbin2.controller;

import com.airbnb.airbin2.entity.User;
import com.airbnb.airbin2.payload.LoginDto;
import com.airbnb.airbin2.payload.UserDto;
import com.airbnb.airbin2.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addUser(@RequestBody UserDto userDto){
        User user = userService.addUser(userDto);
        return new ResponseEntity<>("User registered successfully!", HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto){
        String token = userService.verifyLogin(loginDto);
        if(token != null){
            return new ResponseEntity<>(token,HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Invalid login credential",HttpStatus.UNAUTHORIZED);
        }
    }
}
