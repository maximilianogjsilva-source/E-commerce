package com.max.carpincho.security.service;

import com.max.carpincho.security.persistence.entity.RoleEntity;
import com.max.carpincho.security.persistence.entity.RoleEnum;
import com.max.carpincho.security.persistence.repository.RoleEntityRepository;
import com.max.carpincho.security.service.interfaces.IRoleEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class RoleEntityServiceImpl implements IRoleEntityService {

    @Autowired
    private RoleEntityRepository roleRepository;

    @Override
    public Boolean existsAnyRoleByName(String roleName) {
        return this.roleRepository.existsRoleEntityByRoleEnum(RoleEnum.valueOf(roleName));
    }

    @Override
    public Boolean existsTheseRolesByNames(List<String> listNameRoles) {
        return listNameRoles.stream()
                .allMatch(this::existsAnyRoleByName);
    }

    @Override
    public Set<RoleEntity> findRoleEntitiesByRoleNames(List<String> listRoleNames) {
        return this.roleRepository.findRoleEntitiesByRoleEnumIn(listRoleNames);
    }

}
