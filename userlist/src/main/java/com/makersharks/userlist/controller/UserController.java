package com.makersharks.userlist.controller;

import com.makersharks.userlist.dto.UserDTO;
import com.makersharks.userlist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/user/register")
    public ResponseEntity<Object> createUser(@Validated @RequestBody UserDTO user){
        ResponseEntity<Object> createdUser = userService.createUser(user);
        return createdUser;
    }

    @GetMapping("/user/fetch")
    public ResponseEntity<UserDTO> getUserByUsername(@RequestParam(name="username", required=false) String username){
        UserDTO user = userService.getUser(username);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

}
