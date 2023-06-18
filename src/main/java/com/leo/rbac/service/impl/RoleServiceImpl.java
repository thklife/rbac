package com.leo.rbac.service.impl;

import com.leo.rbac.entity.Role;
import com.leo.rbac.dao.RoleMapper;
import com.leo.rbac.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}
