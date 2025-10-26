package com.max.carpincho.security.persistence.repository;

import com.max.carpincho.security.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findUserEntityByUsername(String username);

}
