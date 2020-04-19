package com.dati.service;

import com.alibaba.fastjson.JSON;
import com.dati.Utils.ExcepUtils;
import com.dati.Utils.ResultUtils;
import com.dati.dao.GroupDao;
import com.dati.pojo.GroupTopic;
import com.dati.responseresult.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class GroupServiceImpl implements GroupService{
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private GroupDao groupDao;
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Result<GroupTopic> findById(String id) {
        List<GroupTopic> byId =  null;
        String s = redisTemplate.opsForValue().get(id);
        if (StringUtils.isNotEmpty(s)) {
            byId = JSON.parseObject(s, List.class);
        } else {
            byId = groupDao.findById(id);
            redisTemplate.opsForValue().set(id, JSON.toJSONString(byId), 24, TimeUnit.HOURS);
        }
        Result<GroupTopic> groupResponse = new Result<GroupTopic>(ResultUtils.QUERY_WAS_SUCCESS, byId);
        return groupResponse;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Result addGroup(GroupTopic groupTopic) {
        if (StringUtils.isEmpty(groupTopic.getGroup_name())) {
            ExcepUtils.createException(ResultUtils.GROUP_NOLL);
        }

        if (StringUtils.isEmpty(groupTopic.getClass_id())) {
            ExcepUtils.createException(ResultUtils.CLASS_NOLL);
        }

        int byName = groupDao.findByName(groupTopic.getGroup_name());

        if (byName > 0){
            ExcepUtils.createException(ResultUtils.GROUP_ERROR);
        }

        String replace = UUID.randomUUID().toString().replace("-", "");
        groupTopic.setId(replace);
        int i = groupDao.addGroup(groupTopic);
        if (i <= 0) {
            ExcepUtils.createException(ResultUtils.ADD_GROUP_ERROR);
        }
        //刷新缓存
        String s = JSON.toJSONString(groupDao.findById(groupTopic.getClass_id()));
        redisTemplate.opsForValue().set(groupTopic.getClass_id(), s, 24, TimeUnit.HOURS);
        return new Result(ResultUtils.ADD_GROUP_SUCCESS);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Result update(GroupTopic groupTopic) {
        if (StringUtils.isEmpty(groupTopic.getId())) {
            ExcepUtils.createException(ResultUtils.UPDATE_ERROR);
        }

        if (StringUtils.isEmpty(groupTopic.getGroup_name())) {
            ExcepUtils.createException(ResultUtils.UPDATE_ERROR);
        }

        int i1 = groupDao.findByNotId(groupTopic);

        if (i1 > 0) {
            ExcepUtils.createException(ResultUtils.UPDATE_HAVE);
        }

        int i = groupDao.update(groupTopic);
        if (i <= 0) {
            ExcepUtils.createException(ResultUtils.UPDATE_ERROR);
        }
        String class_id = groupDao.class_id(groupTopic.getId());
        //刷新缓存
        String s = JSON.toJSONString(groupDao.findById(class_id));
        redisTemplate.opsForValue().set(class_id, s, 24, TimeUnit.HOURS);
        return new Result(ResultUtils.SUCCESS);
    }
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    @Override
    public Result delete(String id) {
        String class_id = groupDao.class_id(id);
        int i = groupDao.queryTopic(id);
        if (i > 0) {
            ExcepUtils.createException(ResultUtils.TOPIC_HAVE);
        }
        groupDao.delete(id);
        //刷新当前班级的分类缓存
        String s = JSON.toJSONString(groupDao.findById(class_id));
        redisTemplate.opsForValue().set(class_id, s, 24, TimeUnit.HOURS);
        return new Result(ResultUtils.SUCCESS);
    }
}
