package com.leo.rbac.dao;

import com.leo.rbac.entity.Permission;
import com.leo.rbac.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author leo
 * @since 2023-06-18
 */
public interface RoleMapper extends BaseMapper<Role> {
    /**
     * 根据用户ID查询权限列表
     * @param userId
     * @return
     */
    List<Permission> findPermissionListByUserId(Long userId);
}
