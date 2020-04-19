package com.dati.service;

import com.dati.pojo.Role;
import com.dati.pojo.User;
import com.dati.responseresult.Result;
import com.dati.responseresult.UserResponse;
import org.apache.ibatis.annotations.Select;

public interface ManageService {
    UserResponse getUser();
    Result<User> getUsers(String class_id);
    Result<User> findByClassIdAndNotId(String class_id, String id);
    Result<Role> queryRole(String userId);
    Result<Role> queryRole1(String userId);
    Result addRole(String userId, String roleId);
    Result deleteRole(String userId, String roleId);

}
