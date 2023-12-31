package com.leo.rbac.service.impl;

import com.leo.rbac.entity.Department;
import com.leo.rbac.dao.DepartmentMapper;
import com.leo.rbac.service.DepartmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements DepartmentService {

}
