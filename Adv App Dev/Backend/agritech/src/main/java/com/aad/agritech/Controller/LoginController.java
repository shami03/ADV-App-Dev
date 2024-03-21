package com.aad.agritech.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.aad.agritech.Dto.LoginRequest;
import com.aad.agritech.Model.Users;
import com.aad.agritech.Service.UserService;

@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/api/login")
    public ResponseEntity<Users> login(@RequestBody LoginRequest loginRequest) {
        return userService.login(loginRequest);
    }
}

