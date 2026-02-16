package com.popkovalex.discogs_stats.services;

import com.popkovalex.discogs_stats.dto.UserCreateDto;
import com.popkovalex.discogs_stats.dto.UserDto;
import com.popkovalex.discogs_stats.mappers.UserMapper;
import com.popkovalex.discogs_stats.models.User;
import com.popkovalex.discogs_stats.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public UserDto registerUser(UserCreateDto userCreateDto){
        //Check on emptiness
        if (userCreateDto.getUsername().isEmpty()){
            throw new RuntimeException("Username is empty");
        }
        if (userCreateDto.getEmail().isEmpty()){
            throw new RuntimeException("Email is empty");
        }
        if  (userCreateDto.getPassword().isEmpty()){
            throw new RuntimeException("Password is empty");
        }
        if  (userCreateDto.getConfirmPassword().isEmpty()){
            throw new RuntimeException("You must confirm your password");
        }

        //Validations
        if (!userCreateDto.getUsername().matches("^[a-zA-Z0-9_-]{3,20}$")){
            throw new RuntimeException("Username is not valid");
        }

        if (!userCreateDto.getEmail().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\\\.[a-zA-Z]{2,}$")){
            throw new RuntimeException("Email is not valid");
        }

        if (!userCreateDto.getPassword().equals(userCreateDto.getConfirmPassword())){
            throw new RuntimeException("Passwords do not match");
        }

        Optional<User> optUser = userRepository.findUserByName(userCreateDto.getUsername());
        if (optUser.isPresent()){
            throw new RuntimeException("Username already exists");
        }

        optUser = userRepository.findUserByEmail(userCreateDto.getEmail());
        if (optUser.isPresent()){
            throw new RuntimeException("Email already exists");
        }

        UserMapper  userMapper = new UserMapper();
        User user = userMapper.userRegistrationMap(userCreateDto);

        userRepository.save(user);

        return userMapper.toResponseDto(user);
    }
}
