package com.popkovalex.discogs_stats.controllers;


import com.popkovalex.discogs_stats.dto.UserCreateDto;
import com.popkovalex.discogs_stats.dto.UserDto;
import com.popkovalex.discogs_stats.repository.UserRepository;
import com.popkovalex.discogs_stats.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody UserCreateDto userCreateDto){
        return ResponseEntity.ok(userService.registerUser(userCreateDto));
    }
}
