package com.makersharks.userlist.service;

import com.makersharks.userlist.Utils.Utility;
import com.makersharks.userlist.dto.UserDTO;
import com.makersharks.userlist.entity.User;
import com.makersharks.userlist.exceptions.EntityNotFoundException;
import com.makersharks.userlist.exceptions.GenericBizException;
import com.makersharks.userlist.repository.UserJpaRespository;
import com.sun.net.httpserver.Authenticator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserJpaRespository userRepo;

    @Autowired
    public PasswordEncoder passwordEncoder;
    public ResponseEntity<Object> createUser(UserDTO requestUser) {
        User user = new User();
        user.setUsername(requestUser.getName());
        user.setUserPassword(passwordEncoder.encode(requestUser.getPassword()));
        user.setEmail(requestUser.getEmail());

        Optional<User> userFromDBOpt = userRepo.findByUsername(requestUser.getName());


        if(userFromDBOpt.isPresent()){
            throw new GenericBizException("User not created. User with username " + requestUser.getName() + " already exists. ");
        }

        Optional<User> userByEmailDBOpt = userRepo.findByEmail(requestUser.getEmail());

        if(userByEmailDBOpt.isPresent()){
            throw new GenericBizException("User not created. User with email " + requestUser.getEmail() + " already exists. ");
        }

        User createdUser = userRepo.save(user);
        Map<String,String> response = new HashMap<String, String>();
        response.put("status", "OK");
        response.put("details", "User "+createdUser.getUsername()+" registered");
        return ResponseEntity.ok(response);


    }

    public UserDTO getUser(String username) {
        User user;
        UserDTO userDTO = new UserDTO();
        if(!Utility.isNullOrEmpty(username)) {
            Optional<User> userOptional = userRepo.findByUsername(username);
            if(userOptional.isPresent()) {
                user=userOptional.get();
                userDTO = new UserDTO(user.getUsername(), user.getUserPassword(), user.getEmail());
            }else{
                throw new EntityNotFoundException("User with username " + username +" not found");
            }
        }
        return userDTO;
    }
}
