package com.max.carpincho.security.service;

import com.max.carpincho.security.controller.dto.AuthCreateUserDevRequest;
import com.max.carpincho.security.controller.dto.AuthUserRequest;
import com.max.carpincho.security.controller.dto.AuthResponse;
import com.max.carpincho.security.persistence.entity.UserEntity;
import com.max.carpincho.security.service.interfaces.IAuthResponseService;
import com.max.carpincho.security.service.interfaces.IRoleEntityService;
import com.max.carpincho.security.service.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthRessponseServiceImpl implements IAuthResponseService {

    @Autowired
    private UserEntityServiceImpl userService;

    @Autowired
    private IRoleEntityService roleService;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public AuthResponse createUserDev(AuthCreateUserDevRequest authCreateUserRequest) {

        if(!roleService.existsTheseRolesByNames(authCreateUserRequest.roles())){
            throw new IllegalArgumentException("The roles specified does not exist");
        }


        UserEntity userCreated = this.userService.save(
                authCreateUserRequest.username(),
                this.passwordEncoder.encode(authCreateUserRequest.password()),
                roleService.findRoleEntitiesByRoleNames(authCreateUserRequest.roles())
        );

        String token = this.authenticateUser(authCreateUserRequest.username(), authCreateUserRequest.password());

        return new AuthResponse(userCreated.getUsername(),
                "User created successfuly",
                token,
                true);
    }

    @Override
    public AuthResponse loginUser(AuthUserRequest authUserRequest) {

        String token = this.authenticateUser(authUserRequest.username(), authUserRequest.password());

        if(!token.equals("")){
            return  new AuthResponse(authUserRequest.username(), "Login successfuly", token, true);
        } else {
            throw new IllegalArgumentException("Invalid username or password");
        }
    }

    @Override
    public AuthResponse createUser(AuthUserRequest authUserRequest) {
        UserEntity userCreated = this.userService.save(authUserRequest.username(),
                this.passwordEncoder.encode(authUserRequest.password()),
                this.roleService.findRoleEntitiesByRoleNames(List.of("USER")));

        String token = this.authenticateUser(authUserRequest.username(), authUserRequest.password());

        return new AuthResponse(userCreated.getUsername(),
                "User created successfuly",
                token,
                true);
    }


    private String authenticateUser(String username, String password) {

        UserEntity userEntity = this.userService.getByUsername(username).orElseThrow(()->
                new IllegalArgumentException("Username not found")
        );

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                username,
                userEntity.getPassword(),
                this.userService.getAuthorities(userEntity)
        );

        if (!passwordEncoder.matches(password, userEntity.getPassword())) {
            throw  new IllegalArgumentException("Invalid password");
        }
            return jwtUtils.createToken(authentication);
    }


}
