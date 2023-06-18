package com.leo.rbac.controller;


import com.leo.rbac.service.UserService;
import com.leo.rbac.utils.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Resource
    private UserService userService;
    //查询所有用户信息
    @GetMapping("/list")
    public Result listAll(){
        return Result.ok(userService.list());
    }
}

