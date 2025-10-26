package com.max.carpincho.security.service.interfaces;

import com.max.carpincho.security.persistence.entity.RoleEntity;

import java.util.List;
import java.util.Set;

public interface IRoleEntityService {

    Boolean existsAnyRoleByName(String roleName);

    Boolean existsTheseRolesByNames(List<String> listNameRoles);

    Set<RoleEntity> findRoleEntitiesByRoleNames(List<String> listRoleNames);

}
