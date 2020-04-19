package com.dati.controller;

import com.dati.pojo.Record;
import com.dati.request.BlankVo;
import com.dati.request.RecordVo;
import com.dati.responseresult.Result;
import com.dati.service.RecordService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topic")
public class RecordController {
    @Autowired
    private RecordService service;
    //单选题和判断题目使用相同接口。
//添加答题记录（单选）
    @PostMapping("/singleRecord")
    public Result addRecord(@RequestBody RecordVo recordVo){
        return service.addRecordSingle(recordVo);
    }
//添加填空题记录
    @PostMapping("/blankRecord")
    public Result addRecord(@RequestBody BlankVo blankVo){
        return service.addRecordBlank(blankVo);
    }
//查询答题记录
    @GetMapping("/pageRecord")
    public Result<Record> recordResult(@RequestParam(value = "page",required = false, defaultValue = "1") Integer page,
                                       @RequestParam(value = "size",required = false, defaultValue = "10") Integer size,
                                       @RequestParam(value = "user_id") String user_id,
                                       @RequestParam(value = "group_topic_id",required = false) String group_topic_id,
                                       @RequestParam(value = "title", required = false) String title
                                       ){
       return service.queryPage(page, size, user_id, group_topic_id, title);
    }
//查询所有人的答题历史
    @RequiresPermissions(value = "history")
    @GetMapping("/pageAllRecord")
    public Result<Record> recordResult(@RequestParam(value = "page",required = false, defaultValue = "1") Integer page,
                                       @RequestParam(value = "size",required = false, defaultValue = "10") Integer size,
                                       @RequestParam(value = "user_id") String user_id,
                                       @RequestParam(value = "group_topic_id") String group_topic_id
    ){
        return service.queryAllPage(page, size, user_id, group_topic_id);
    }

}
