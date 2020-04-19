package com.dati.controller;

import com.dati.pojo.GroupTopic;
import com.dati.responseresult.Result;
import com.dati.service.GroupService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topic")
public class GroupController {
    @Autowired
    private GroupService groupService;
//查询分类
    @GetMapping("/groups/{id}")
    public Result<GroupTopic> findById(@PathVariable(value = "id") String id){
        return groupService.findById(id);
    }
//添加分类
    @RequiresPermissions(value = "problem")
    @PostMapping("/groups")
    public Result addGroup(@RequestBody GroupTopic groupTopic){
       return groupService.addGroup(groupTopic);
    }
//改变分类名称
    @RequiresPermissions(value = "problem")
    @PutMapping("/groups/{id}")
    public Result update(@PathVariable("id") String id, @RequestBody GroupTopic groupTopic){
        groupTopic.setId(id);
        return groupService.update(groupTopic);
    }
    //删除分类
    @RequiresPermissions(value = "problem")
    @DeleteMapping("/groups/{id}")
    public Result delete(@PathVariable("id") String id) {
        return groupService.delete(id);
    }
}
