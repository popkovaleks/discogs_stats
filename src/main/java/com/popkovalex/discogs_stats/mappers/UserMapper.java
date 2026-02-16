package com.popkovalex.discogs_stats.mappers;


import com.popkovalex.discogs_stats.dto.UserCreateDto;
import com.popkovalex.discogs_stats.dto.UserDto;
import com.popkovalex.discogs_stats.models.User;
import com.popkovalex.discogs_stats.util.PasswordHasher;

public class UserMapper {

    public User userRegistrationMap (UserCreateDto userCreateDto){
        User user = new User();
        user.setEmail(userCreateDto.getEmail());
        user.setName(userCreateDto.getUsername());

        String generatedSalt = PasswordHasher.generateSalt();
        user.setPassword(PasswordHasher.hashPassword(userCreateDto.getPassword(), generatedSalt));
        user.setSalt(generatedSalt);

        return user;
    }

    public UserDto toResponseDto (User user) {
        UserDto userDto = new UserDto();
        userDto.setUsername(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setDiscogsName(user.getDiscogsName());
        return userDto;
    }
}
