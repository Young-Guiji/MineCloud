package com.springboot.auth.authorization.provider;

import com.springboot.auth.authorization.entity.Role;
import com.springboot.auth.authorization.entity.User;
import com.springboot.cloud.common.core.entity.vo.Result;
import com.springboot.cloud.common.core.entity.organization.dto.UserLogDto;

import java.util.HashSet;
import java.util.Set;

public class OrganizationProviderFallback implements OrganizationProvider {

    @Override
    public Result<User> getUserByUniqueId(String uniqueId) {
        return Result.success(new User());
    }

    @Override
    public Result<Set<Role>> queryRolesByUserId(String userId) {
        return Result.success(new HashSet<Role>());
    }

    @Override
    public Result<Integer> saveUserLog(UserLogDto userLogDto) {
        return Result.success(0);
    }
}
