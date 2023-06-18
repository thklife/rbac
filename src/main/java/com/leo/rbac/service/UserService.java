package com.leo.rbac.service;

import com.leo.rbac.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;


public interface UserService extends IService<User> {
    User findUserByUserName(String userName);
}
