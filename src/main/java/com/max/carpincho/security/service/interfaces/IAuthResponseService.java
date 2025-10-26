package com.max.carpincho.security.service.interfaces;

import com.max.carpincho.security.controller.dto.AuthCreateUserDevRequest;
import com.max.carpincho.security.controller.dto.AuthUserRequest;
import com.max.carpincho.security.controller.dto.AuthResponse;

public interface IAuthResponseService {

    AuthResponse createUserDev(AuthCreateUserDevRequest authCreateUserRequest);

    AuthResponse loginUser(AuthUserRequest authUserRequest);

    AuthResponse createUser(AuthUserRequest authUserRequest);

}
