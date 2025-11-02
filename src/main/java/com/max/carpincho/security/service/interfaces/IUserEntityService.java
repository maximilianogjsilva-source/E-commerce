package com.max.carpincho.security.service.interfaces;

import com.max.carpincho.security.persistence.entity.RoleEntity;
import com.max.carpincho.security.persistence.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface IUserEntityService {

    Optional<UserEntity> getByUsername(String username);

    UserEntity save(UserEntity userEntity);

    UserEntity save(String username, String password, Set<RoleEntity> roles);

    List<GrantedAuthority> getAuthorities(UserEntity userEntity);

    List<UserEntity> setUp();

}
