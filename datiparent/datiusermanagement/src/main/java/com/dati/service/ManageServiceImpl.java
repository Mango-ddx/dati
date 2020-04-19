package com.dati.service;

import com.dati.Utils.ExcepUtils;
import com.dati.Utils.ResultUtils;
import com.dati.dao.RoleDao;
import com.dati.dao.UserDao;
import com.dati.pojo.Permission;
import com.dati.pojo.Role;
import com.dati.pojo.User;
import com.dati.responseresult.Result;
import com.dati.responseresult.UserResponse;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ManageServiceImpl implements ManageService {
  @Autowired
  private UserDao userDao;

  @Autowired
  private RoleDao roleDao;
    public UserResponse getUser() {
        User principal = (User)SecurityUtils.getSubject().getPrincipal();
        UserResponse userResponse = new UserResponse();
        userResponse.setCode(200);
        userResponse.setMessage("查询成功");
        userResponse.setSuccess(true);
        User user = new User();
        user.setUsername(principal.getUsername());
        user.setClass_id(principal.getClass_id());
        user.setId(principal.getId());
        List<Permission> permissions = new ArrayList<>();
        for (Role role : principal.getRoles()) {
            //调用role.getPermissions()报错。
            for (Permission permission : role.getPermissions()) {
               permissions.add(permission);
           }
        }
        Set<String> collect = permissions.stream().map(i -> i.getId()).collect(Collectors.toSet());
        userResponse.setper_id(collect);
        userResponse.setUser(user);
        return userResponse;
    }

    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public Result<User> getUsers(String class_id) {
        List<User> byClass_id = userDao.findByClass_id(class_id);
        return new Result<User>(ResultUtils.SUCCESS, byClass_id);
    }

    @Override
    public Result<User> findByClassIdAndNotId(String class_id, String id) {
        List<User> byClass_idAndNotId = userDao.findByClass_idAndNotId(class_id, id);
        return new Result<User>(ResultUtils.SUCCESS, byClass_idAndNotId);
    }

    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public Result<Role> queryRole(String userId) {
        List<Role> roles = roleDao.queryRole(userId);
        if (roles.isEmpty()) {
            ExcepUtils.createException(ResultUtils.ROLE_NULL);
        }

        return new Result<Role>(ResultUtils.SUCCESS, roles);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Result addRole(String userId, String roleId) {
        int byId = userDao.findById(userId);
        if (byId <= 0) ExcepUtils.createException(ResultUtils.ERROR);
        int byId1 = roleDao.findById(roleId);
        if (byId1 <= 0) ExcepUtils.createException(ResultUtils.ERROR);
        int role = roleDao.isRole(roleId, userId);
        if (role > 0) ExcepUtils.createException(ResultUtils.ROLE_HAVE);
        int i = roleDao.addRole(roleId, userId);
        if (i <= 0)ExcepUtils.createException(ResultUtils.ERROR);
        return new Result(ResultUtils.SUCCESS);
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Result<Role> queryRole1(String userId) {
        List<Role> roles = roleDao.queryRole1(userId);
        if (roles.isEmpty()) ExcepUtils.createException(ResultUtils.ROLE_NOT);
        return new Result<Role>(ResultUtils.SUCCESS, roles);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Result deleteRole(String userId, String roleId) {
        int i = roleDao.deleteRole(roleId, userId);
        if (i <= 0) ExcepUtils.createException(ResultUtils.ERROR);
        return new Result(ResultUtils.SUCCESS);
    }
}
