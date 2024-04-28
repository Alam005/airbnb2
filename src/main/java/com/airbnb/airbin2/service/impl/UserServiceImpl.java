package com.airbnb.airbin2.service.impl;

import com.airbnb.airbin2.entity.User;
import com.airbnb.airbin2.exception.UserAlreadyExistsException;
import com.airbnb.airbin2.mapper.UserMapper;
import com.airbnb.airbin2.payload.LoginDto;
import com.airbnb.airbin2.payload.UserDto;
import com.airbnb.airbin2.repository.UserRepository;
import com.airbnb.airbin2.service.UserService;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private UserMapper userMapper;

    private JWTService jwtService;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, JWTService jwtService) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.jwtService = jwtService;
    }

    //CREATE A USER
    @Transactional
    public User addUser(UserDto userDto){
        Optional<User> opUser = userRepository.findByEmail(userDto.getEmail());
        if(opUser.isPresent()){
            throw new UserAlreadyExistsException("User already found in database!");
        }
        User user = userMapper.mapToEntity(userDto);
        User savedUser = userRepository.save(user);
        return savedUser;
    }

    @Override
    public String verifyLogin(LoginDto loginDto) {

        Optional<User> opUser = userRepository.findByEmail(loginDto.getEmail());
        if(opUser.isPresent()){
            User user = opUser.get();
            boolean checkpw = BCrypt.checkpw(loginDto.getPassword(), user.getPassword());
            if(checkpw){
                String token = jwtService.generateToken(user);
                return token;
            }
        }
        return null;
    }
}
