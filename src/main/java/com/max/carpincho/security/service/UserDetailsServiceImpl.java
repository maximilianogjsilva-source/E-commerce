package com.max.carpincho.security.service;

import com.max.carpincho.security.persistence.entity.UserEntity;
import com.max.carpincho.security.service.interfaces.IUserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private IUserEntityService userEntityService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = this.userEntityService.getByUsername(username).get();

        List<GrantedAuthority> authorityList = this.userEntityService.getAuthorities(userEntity);

        return new User(userEntity.getUsername(),
                userEntity.getPassword(),
                userEntity.getIsEnabled(),
                userEntity.getAccountNoExpired(),
                userEntity.getCredentialNoExpired(),
                userEntity.getAccountNoLocked(),
                authorityList);
    }
}
