package com.leo.rbac.dao;

import com.leo.rbac.entity.Permission;
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
public interface PermissionMapper extends BaseMapper<Permission> {
    List<Permission> findPermissionListByUserId(Long userId);
}
