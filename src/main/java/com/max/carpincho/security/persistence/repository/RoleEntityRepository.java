package com.max.carpincho.security.persistence.repository;

import com.max.carpincho.security.persistence.entity.RoleEntity;
import com.max.carpincho.security.persistence.entity.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface RoleEntityRepository extends JpaRepository<RoleEntity, Long> {

    Boolean existsRoleEntityByRoleEnum(RoleEnum roleEnum);

    Set<RoleEntity> findRoleEntitiesByRoleEnumIn(List<String> roleNames);

}
