package com.dati.controller;

import com.dati.responseresult.Result;
import com.dati.responseresult.UserResponse;
import com.dati.service.ManageService;
import org.apache.ibatis.annotations.Delete;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/manage")
public class ManageController {
    @Autowired
    private ManageService manageService;
//初始化
    @GetMapping("/users")
    public UserResponse getUser(){
        return manageService.getUser();
    }
//查询当前班级其他人
    @RequiresPermissions(value = "history")
    @GetMapping("/users/{class_id}")
    public Result getUsers(@PathVariable("class_id") String class_id){
      return manageService.getUsers(class_id);
    }

//查询除了自己以外的其他人。
    @RequiresPermissions(value = "role_manager")
    @GetMapping("/users/{class_id}/{user_id}")
    public Result findByClassId(@PathVariable("class_id") String class_id, @PathVariable("user_id") String user_id){
        return manageService.findByClassIdAndNotId(class_id, user_id);
    }
//查询没有的角色
    @RequiresPermissions(value = "role_manager")
    @GetMapping("/roles/{userId}")
    public Result queryRole(@PathVariable("userId")String userId){
        return manageService.queryRole(userId);
    }
//查询已经有的角色
    @RequiresPermissions(value = "role_manager")
    @GetMapping("/roles1/{userId}")
    public Result queryRole1(@PathVariable("userId")String userId){
        return manageService.queryRole1(userId);
    }
//添加角色
    @RequiresPermissions(value = "role_manager")
    @PostMapping("/roles")
    public Result addRole(@RequestParam("user_id") String user_id,@RequestParam("role_id") String role_id){
       return manageService.addRole(user_id, role_id);
    }
//删除指定角色
    @RequiresPermissions(value = "role_manager")
    @DeleteMapping("/roles/{user_id}/{role_id}")
    public Result deleteRole(@PathVariable("user_id") String user_id,@PathVariable("role_id") String role_id){
        Result result = manageService.deleteRole(user_id, role_id);
        return result;
    }
}
