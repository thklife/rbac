package com.leo.rbac.service;

import com.leo.rbac.entity.Permission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author leo
 * @since 2023-06-18
 */
public interface PermissionService extends IService<Permission> {
    List<Permission> findPermissionListByUserId(Long userId);
}
