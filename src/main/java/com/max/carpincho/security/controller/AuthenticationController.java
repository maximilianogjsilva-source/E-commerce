package com.max.carpincho.security.controller;

import com.max.carpincho.security.controller.dto.AuthCreateUserDevRequest;
import com.max.carpincho.security.controller.dto.AuthUserRequest;
import com.max.carpincho.security.controller.dto.AuthResponse;
import com.max.carpincho.security.service.AuthRessponseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(value = "http://localhost:4200")
public class AuthenticationController {

    @Autowired
    private AuthRessponseServiceImpl authRessponseService;

    @PostMapping("/sign-up-dev")
    public ResponseEntity<AuthResponse> createUserDev(
            @Validated @RequestBody AuthCreateUserDevRequest createUserRequest) {
        return new ResponseEntity<AuthResponse>(authRessponseService.createUserDev(createUserRequest), HttpStatus.CREATED);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<AuthResponse> createUser(@Validated @RequestBody AuthUserRequest authUserRequest) {
        return new ResponseEntity<AuthResponse>(authRessponseService.createUser(authUserRequest) ,HttpStatus.CREATED);
    }


    @PostMapping("/log-in")
    public ResponseEntity<AuthResponse> loginUser(@Validated @RequestBody AuthUserRequest authUserRequest) {
        return new ResponseEntity<AuthResponse>(authRessponseService.loginUser(authUserRequest) ,HttpStatus.OK);
    }

}
