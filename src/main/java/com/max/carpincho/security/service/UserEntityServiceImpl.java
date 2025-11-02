package com.max.carpincho.security.service;

import com.max.carpincho.security.persistence.entity.PermissionEntity;
import com.max.carpincho.security.persistence.entity.RoleEntity;
import com.max.carpincho.security.persistence.entity.RoleEnum;
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

    @Override
    public List<UserEntity> setUp(){
        			// Create PERMISSIONS
			PermissionEntity createPermission = PermissionEntity.builder()
					.name("CREATE").build();

			PermissionEntity readPermission = PermissionEntity.builder()
					.name("READ").build();

			PermissionEntity updatePermission = PermissionEntity.builder()
					.name("UPDATE").build();

			PermissionEntity deletePermission = PermissionEntity.builder()
					.name("DELETE").build();

			PermissionEntity refactorPermission = PermissionEntity.builder()
					.name("REFACTOR").build();

			//Create ROLES
			RoleEntity roleAdmin = RoleEntity.builder()
					.roleEnum(RoleEnum.ADMIN)
					.permissionList(Set.of(createPermission, readPermission, updatePermission, deletePermission))
					.build();

			RoleEntity roleUser = RoleEntity.builder()
					.roleEnum(RoleEnum.USER)
					.permissionList(Set.of(createPermission, readPermission))
					.build();

			RoleEntity roleInvited = RoleEntity.builder()
					.roleEnum(RoleEnum.INVITED)
					.permissionList(Set.of(readPermission))
					.build();

			RoleEntity roleDeveloper = RoleEntity.builder()
					.roleEnum(RoleEnum.DEVELOPER)
					.permissionList(Set.of(
							createPermission, readPermission, updatePermission, deletePermission, refactorPermission))
					.build();

			//Create USERS
			UserEntity userSantiago = UserEntity.builder()
					.username("Santiago")
					.password("$2a$10$VthJCDyMhW/HGFrW9G7tGOw0wYAhEdVlJ8XwLWh0Xgilz/KSYxkGC")
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(roleAdmin))
					.build();

			UserEntity userDaniel = UserEntity.builder()
					.username("Daniel")
					.password("$2a$10$VthJCDyMhW/HGFrW9G7tGOw0wYAhEdVlJ8XwLWh0Xgilz/KSYxkGC")
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(roleUser))
					.build();

			UserEntity userAndrea = UserEntity.builder()
					.username("Andrea")
					.password("$2a$10$VthJCDyMhW/HGFrW9G7tGOw0wYAhEdVlJ8XwLWh0Xgilz/KSYxkGC")
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(roleInvited))
					.build();

			UserEntity userAnyi = UserEntity.builder()
					.username("Anyi")
					.password("$2a$10$VthJCDyMhW/HGFrW9G7tGOw0wYAhEdVlJ8XwLWh0Xgilz/KSYxkGC")
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(roleDeveloper))
					.build();

			return userRepository.saveAll(List.of(userSantiago, userDaniel, userAndrea, userAnyi));
    }

}
