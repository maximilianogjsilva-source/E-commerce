package com.max.carpincho.security.service;

import com.max.carpincho.security.persistence.entity.RoleEntity;
import com.max.carpincho.security.persistence.entity.UserEntity;
import com.max.carpincho.security.persistence.repository.UserEntityRepository;
import com.max.carpincho.security.service.interfaces.IUserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserEntityServiceImpl implements IUserEntityService {

    @Autowired
    private UserEntityRepository userRepository;

    @Override
    public UserEntity save(UserEntity userEntity){
        return this.userRepository.save(userEntity);
    }

    @Override
    public UserEntity save(String username, String password, Set<RoleEntity> roles) {
        UserEntity userEntity = UserEntity.builder()
                .username(username)
                .password(password)
                .roles(roles)
                .isEnabled(true)
                .accountNoExpired(true)
                .accountNoLocked(true)
                .credentialNoExpired(true)
                .build();

        return this.userRepository.save(userEntity);
    }

    @Override
    public Optional<UserEntity> getByUsername(String username) {
        return this.userRepository.findUserEntityByUsername(username);
    }

    @Override
    public List<GrantedAuthority> getAuthorities(UserEntity userEntity) {
        List<GrantedAuthority> athorities = new ArrayList<>();

        userEntity.getRoles().forEach(role -> athorities.add(
                new SimpleGrantedAuthority("ROLE_".concat(role.getRoleEnum().name())
                )));
        userEntity.getRoles().stream()
                .flatMap(role -> role.getPermissionList().stream())
                .forEach(permission -> athorities.add(
                        new SimpleGrantedAuthority(permission.getName())));

        return athorities;
    }
}
