package com.dati.controller;

import com.dati.request.BlankVo;
import com.dati.request.JudgmentVo;
import com.dati.request.SingleVo;
import com.dati.pojo.Topics;
import com.dati.responseresult.Result;
import com.dati.responseresult.ResultOne;
import com.dati.service.TopicService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topic")
public class TopicController {
    @Autowired
    private TopicService service;
//添加单选题
    @RequiresPermissions(value = "problem")
    @PostMapping("/single")
    public Result addSingle(@RequestBody SingleVo singleVo){
        return service.addSingle(singleVo);
    }
//添加填空题
    @RequiresPermissions(value = "problem")
    @PostMapping("/blank")
    public Result addBlank(@RequestBody BlankVo blankVo){
        return service.addBlank(blankVo);
    }

//添加判断题
    @RequiresPermissions(value = "problem")
    @PostMapping("/judgment")
    public Result addJudgment(@RequestBody JudgmentVo judgmentVo){
        return service.addJudgment(judgmentVo);
    }
//查询指定分类下的题目
    @GetMapping("/topics/{group_id}/{user_id}")
    public ResultOne<Topics> QueryTopic(@PathVariable("group_id") String group_id,
                                         @PathVariable("user_id") String user_id){
        return service.randomQuery(group_id, user_id);
    }
}
