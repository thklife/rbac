package com.leo.rbac.config.security;

import com.leo.rbac.entity.Permission;
import com.leo.rbac.entity.User;
import com.leo.rbac.service.PermissionService;
import com.leo.rbac.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerUserDetailsService implements UserDetailsService {
    @Resource
    private UserService userService;
    @Resource
    private PermissionService permissionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据用户名查询用户信息
        User user = userService.findUserByUserName(username);
        //判断对象是否为空
        if (user == null) {
            throw new UsernameNotFoundException("用户名或密码错误");
        }

        //查询当前登录用户拥有的权限列表
        List<Permission> permissionList = permissionService.findPermissionListByUserId(user.getId());
        //获取权限编码
        List<String> collect = permissionList.stream()
                .filter(item -> item != null)
                .map(item -> item.getCode()).filter(item -> item != null)
                .collect(Collectors.toList());
        //转换成数组
        String[] strings = collect.toArray(new String[collect.size()]);
        //设置权限列表
        List<GrantedAuthority> authorityList =
                AuthorityUtils.createAuthorityList(strings);
        user.setAuthorities(authorityList);
        //设置菜单列表
        user.setPermissionList(permissionList);
        return user;
    }
}
