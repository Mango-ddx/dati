package com.dati.service;

import com.alibaba.fastjson.JSON;
import com.dati.Utils.ExcepUtils;
import com.dati.Utils.ResultUtils;
import com.dati.dao.TopicDao;
import com.dati.request.BlankVo;
import com.dati.request.JudgmentVo;
import com.dati.request.SingleVo;
import com.dati.pojo.Topics;
import com.dati.responseresult.Result;
import com.dati.responseresult.ResultOne;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class TopicServiceImpl implements TopicService {
    @Autowired
    private TopicDao topicDao;
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Result addSingle(SingleVo singleVo) {
        if (singleVo.getAnswer() == null) {
            ExcepUtils.createException(ResultUtils.ADD_TOPIC_ERROR);
        }

        if (singleVo.getGroup_topic_id() == null) {
            ExcepUtils.createException(ResultUtils.ADD_TOPIC_ERROR);
        }

        if (singleVo.getTitle() == null) {
            ExcepUtils.createException(ResultUtils.ADD_TOPIC_ERROR);
        }

        if (singleVo.getTitle_type() == null) {
            ExcepUtils.createException(ResultUtils.ADD_TOPIC_ERROR);
        }

        int byGroupIdAndTitle = topicDao.findByGroupIdAndTitle(singleVo.getGroup_topic_id(), singleVo.getTitle());
        if (byGroupIdAndTitle > 0){
            ExcepUtils.createException(ResultUtils.ADD_TOPIC_REPEAT);
        }

        Topics topics1 = new Topics();
        Map<String, String> map = new LinkedHashMap<>();
        map.put("A", singleVo.getA());
        map.put("B", singleVo.getB());
        map.put("C", singleVo.getC());
        map.put("D", singleVo.getD());
        String option_ans = JSON.toJSONString(map);
        topics1.setAnswer(singleVo.getAnswer());
        topics1.setGroup_topic_id(singleVo.getGroup_topic_id());
        topics1.setTitle(singleVo.getTitle());
        topics1.setTitle_type(singleVo.getTitle_type());
        topics1.setOption_ans(option_ans);
        int i = topicDao.addSingle(topics1);
        if (i <= 0) {
            ExcepUtils.createException(ResultUtils.ADD_TOPIC_ERROR);
        }
        return new Result(ResultUtils.SUCCESS);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Result addBlank(BlankVo blankVo) {
        if (blankVo.getGroup_topic_id() == null) {
            ExcepUtils.createException(ResultUtils.ADD_TOPIC_ERROR);
        }

        if (blankVo.getTitle() == null) {
            ExcepUtils.createException(ResultUtils.ADD_TOPIC_ERROR);
        }

        if (blankVo.getTitle_type() == null) {
            ExcepUtils.createException(ResultUtils.ADD_TOPIC_ERROR);
        }

        if (blankVo.getAnswerBlank() == null) {
            ExcepUtils.createException(ResultUtils.ADD_TOPIC_ERROR);
        }
        int byGroupIdAndTitle = topicDao.findByGroupIdAndTitle(blankVo.getGroup_topic_id(), blankVo.getTitle());
        if (byGroupIdAndTitle > 0){
            ExcepUtils.createException(ResultUtils.ADD_TOPIC_REPEAT);
        }

        String answer = JSON.toJSONString(blankVo.getAnswerBlank());
        Topics topics = new Topics();
        topics.setTitle(blankVo.getTitle());
        topics.setAnswer(answer);
        topics.setGroup_topic_id(blankVo.getGroup_topic_id());
        topics.setTitle_type(blankVo.getTitle_type());
        int i = topicDao.addBlank(topics);
        if (i <= 0)  {
            ExcepUtils.createException(ResultUtils.ADD_TOPIC_ERROR);
        }

        return new Result(ResultUtils.SUCCESS);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Result addJudgment(JudgmentVo judgmentVo) {
        if (judgmentVo.getTitle() == null) {
            ExcepUtils.createException(ResultUtils.ADD_TOPIC_ERROR);
        }

        if (judgmentVo.getAnswer() == null) {
            ExcepUtils.createException(ResultUtils.ADD_TOPIC_ERROR);
        }

        if (judgmentVo.getCorrect() == null) {
            ExcepUtils.createException(ResultUtils.ADD_TOPIC_ERROR);
        }

        if (judgmentVo.getError() == null) {
            ExcepUtils.createException(ResultUtils.ADD_TOPIC_ERROR);
        }

        if (judgmentVo.getAnswer() == null) {
            ExcepUtils.createException(ResultUtils.ADD_TOPIC_ERROR);
        }

        if (judgmentVo.getGroup_topic_id() == null) {
            ExcepUtils.createException(ResultUtils.ADD_TOPIC_ERROR);
        }

        if (judgmentVo.getTitle_type() == null) {
            ExcepUtils.createException(ResultUtils.ADD_TOPIC_ERROR);
        }
        int byGroupIdAndTitle = topicDao.findByGroupIdAndTitle(judgmentVo.getGroup_topic_id(), judgmentVo.getTitle());
        if (byGroupIdAndTitle > 0){
            ExcepUtils.createException(ResultUtils.ADD_TOPIC_REPEAT);
        }
        Map<String, String> map = new LinkedHashMap<>();
        map.put("correct", judgmentVo.getCorrect());
        map.put("error", judgmentVo.getError());
        String jsonString = JSON.toJSONString(map);
        Topics topics = new Topics();
        topics.setTitle_type(judgmentVo.getTitle_type());
        topics.setTitle(judgmentVo.getTitle());
        topics.setGroup_topic_id(judgmentVo.getGroup_topic_id());
        topics.setAnswer(judgmentVo.getAnswer());
        topics.setOption_ans(jsonString);
        int i = topicDao.addJudgment(topics);
        if (i <= 0) {
            ExcepUtils.createException(ResultUtils.ADD_TOPIC_ERROR);
        }
        return new Result(ResultUtils.SUCCESS);
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public ResultOne randomQuery(String group_id, String user_id) {
        if (StringUtils.isEmpty(user_id) || StringUtils.isEmpty(group_id)) {
            ExcepUtils.createException(ResultUtils.QUERY_WAS_ERROR);
        }
        Topics topics = topicDao.randomQuery(group_id, user_id);
        if (topics == null) {
            ExcepUtils.createException(ResultUtils.TOPIC_NULL);
        }
        if (topics.getTitle_type() == 2) {
            List answerList = JSON.parseObject(topics.getAnswer(), List.class);
            topics.setAnswer(String.valueOf(answerList.size()));
        }

        return new ResultOne<Topics>(ResultUtils.SUCCESS, topics);
    }
}
