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

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    //CREATE A USER
    @Transactional
    public User addUser(UserDto userDto){
        Optional<User> user = userRepository.findByEmail(userDto.getEmail());
        if(user.isPresent()){
            throw new UserAlreadyExistsException("User already found in database!");
        }
        User user1 = userMapper.mapToEntity(userDto);
        User savedUser = userRepository.save(user1);
        return savedUser;
    }

    @Override
    public boolean verifyLogin(LoginDto loginDto) {

        Optional<User> opUser = userRepository.findByEmail(loginDto.getEmail());
        if(opUser.isPresent()){
            User user = opUser.get();
            boolean checkpw = BCrypt.checkpw(loginDto.getPassword(), user.getPassword());
            return checkpw;
        }

        return false;
    }
}
