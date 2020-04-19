package com.dati.service;

import com.alibaba.fastjson.JSON;
import com.dati.Utils.ExcepUtils;
import com.dati.Utils.ResultUtils;
import com.dati.dao.GroupDao;
import com.dati.dao.RecordDao;
import com.dati.dao.TopicDao;
import com.dati.pojo.Record;
import com.dati.pojo.Topics;
import com.dati.request.BlankVo;
import com.dati.request.RecordVo;
import com.dati.responseresult.Result;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecordServiceImpl implements RecordService {
    @Autowired
    private RecordDao recordDao;
    @Autowired
    private GroupDao groupDao;
    @Autowired
    private TopicDao topicDao;

    @Transactional(propagation = Propagation.REQUIRED)
    public Result addRecordSingle(RecordVo recordVo) {
        if (StringUtils.isEmpty(recordVo.getAnswer())) {
            ExcepUtils.createException(ResultUtils.ERROR);
        }

        if (StringUtils.isEmpty(recordVo.getTitle())) {
            ExcepUtils.createException(ResultUtils.ERROR);
        }

        if (StringUtils.isEmpty(recordVo.getGroup_topic_id()) || recordVo.getGroup_topic_id().equals("0")) {
            ExcepUtils.createException(ResultUtils.ERROR);
        }

        if (StringUtils.isEmpty(recordVo.getUser_id())) {
            ExcepUtils.createException(ResultUtils.ERROR);
        }

        if (recordVo.getTopics_id() == null) {
            ExcepUtils.createException(ResultUtils.ERROR);
        }

        if (recordVo.getTitle_type() == null) {
            ExcepUtils.createException(ResultUtils.ERROR);
        }
        if (recordDao.QueryUser(recordVo.getUser_id()) <= 0) {
            ExcepUtils.createException(ResultUtils.ERROR);
        }

        if (groupDao.queryGroup(recordVo.getGroup_topic_id()) <= 0) {
            ExcepUtils.createException(ResultUtils.ERROR);
        }

        Record record = new Record();
        record.setRecord_title(recordVo.getTitle());
        record.setUser_id(recordVo.getUser_id());
        record.setGroup_topic_id(recordVo.getGroup_topic_id());
        if (recordDao.queryRecord(record) > 0) {
            ExcepUtils.createException(ResultUtils.ERROR);
        }
        Topics byId = topicDao.findById(recordVo.getTopics_id());
        if (byId == null) {
            ExcepUtils.createException(ResultUtils.ERROR);
        }
        if (byId.getAnswer().equals(recordVo.getAnswer())) {
             record.setCorrect(1);
        } else {
            record.setCorrect(-1);
        }
        record.setUser_answer(recordVo.getAnswer());
        record.setTopics_id(recordVo.getTopics_id());
        record.setTitle_type(recordVo.getTitle_type());
        record.setRecord_answer(byId.getAnswer());
        int i = recordDao.addRecordSingle(record);
        if (i <= 0) ExcepUtils.createException(ResultUtils.ERROR);
        return new Result(ResultUtils.SUCCESS);
    }
    @Transactional(propagation = Propagation.REQUIRED)
    public Result addRecordBlank(BlankVo blankVo) {
        if (blankVo.getAnswerBlank() == null) {
            ExcepUtils.createException(ResultUtils.ERROR);
        }

        if (StringUtils.isEmpty(blankVo.getTitle())) {
            ExcepUtils.createException(ResultUtils.ERROR);
        }

        if (StringUtils.isEmpty(blankVo.getGroup_topic_id()) || blankVo.getGroup_topic_id().equals("0")) {
            ExcepUtils.createException(ResultUtils.ERROR);
        }

        if (StringUtils.isEmpty(blankVo.getUser_id())) {
            ExcepUtils.createException(ResultUtils.ERROR);
        }

        if (blankVo.getTopics_id() == null) {
            ExcepUtils.createException(ResultUtils.ERROR);
        }

        if (blankVo.getTitle_type() == null) {
            ExcepUtils.createException(ResultUtils.ERROR);
        }
        if (recordDao.QueryUser(blankVo.getUser_id()) <= 0) {
            ExcepUtils.createException(ResultUtils.ERROR);
        }

        if (groupDao.queryGroup(blankVo.getGroup_topic_id()) <= 0) {
            ExcepUtils.createException(ResultUtils.ERROR);
        }

        Record record = new Record();
        record.setRecord_title(blankVo.getTitle());
        record.setUser_id(blankVo.getUser_id());
        record.setGroup_topic_id(blankVo.getGroup_topic_id());
        if (recordDao.queryRecord(record) > 0) {
            ExcepUtils.createException(ResultUtils.ERROR);
        }
        Topics byId = topicDao.findById(blankVo.getTopics_id());
        if (byId == null) {
            ExcepUtils.createException(ResultUtils.ERROR);
        }
        List answers  = JSON.parseObject(byId.getAnswer(), List.class);
        boolean correct = isCorrect(answers, blankVo.getAnswerBlank());
        if (correct) {
            record.setCorrect(1);
        } else {
            record.setCorrect(-1);
        }
        List<String> answerBlank = blankVo.getAnswerBlank();
        String s = JSON.toJSONString(answerBlank);
        record.setUser_answer(s);
        record.setTopics_id(blankVo.getTopics_id());
        record.setTitle_type(blankVo.getTitle_type());
        record.setRecord_answer(byId.getAnswer());
        int i = recordDao.addRecordSingle(record);
        if (i <= 0) ExcepUtils.createException(ResultUtils.ERROR);
        return new Result(ResultUtils.SUCCESS);
    }

     private boolean isCorrect(List answers, List userAnswers) {
        if (answers.size() != userAnswers.size()) {
            return false;
        }
        for (int i = 0; i < answers.size(); i++) {
            if (!answers.get(i).equals(userAnswers.get(i))) {
                return false;
            }
        }
        return true;
     }

    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public Result queryPage(Integer page, Integer size, String user_id, String group_topic_id, String title) {
        PageHelper.startPage(page, size);
        if (StringUtils.isEmpty(group_topic_id) || group_topic_id.equals("0")) {
            group_topic_id = null;
        }

        if (StringUtils.isEmpty(title)) {
            title = null;
        }

        if (title != null) {
            title = "%" + title + "%";
        }

        List<RecordVo> recordVos = new ArrayList<>();
        List<Record> records = recordDao.queryPage(user_id, group_topic_id, title);
        for (Record record : records) {
            switch (record.getTitle_type()) {
                case 1:
                    RecordVo recordVo1 = new RecordVo();
                    recordVo1.setTitle(record.getRecord_title());
                    recordVo1.setGroup_name(record.getGroupTopic().getGroup_name());
                    recordVo1.setAnswer(record.getRecord_answer());
                    recordVo1.setUser_answer(record.getUser_answer());
                    if (record.getCorrect() == -1) {
                        recordVo1.setCorrect("错误");
                    } else {
                        recordVo1.setCorrect("正确");
                    }
                    recordVos.add(recordVo1);
                    break;
                case 2:
                    RecordVo recordVo = new RecordVo();
                    recordVo.setGroup_name(record.getGroupTopic().getGroup_name());
                    recordVo.setTitle(record.getRecord_title());
                    String record_answer = record.getRecord_answer();
                    List<String> recordAnswers = JSON.parseObject(record_answer, List.class);
                    StringBuilder stringBuilder = new StringBuilder();
                    for (String str : recordAnswers){
                         stringBuilder.append(str+",");
                    }
                    String s = stringBuilder.toString();
                    recordVo.setAnswer(s.substring(0, s.length()-1));
                    String user_answer = record.getUser_answer();
                    List<String> userAnswer = JSON.parseObject(user_answer, List.class);
                    StringBuilder stringBuilder1 = new StringBuilder();
                    for (String str : userAnswer){
                        stringBuilder1.append(str+",");
                    }
                    String s1 = stringBuilder1.toString();
                    recordVo.setUser_answer(s1.substring(0, s1.length()-1));
                    if (record.getCorrect() == -1) {
                        recordVo.setCorrect("错误");
                    } else {
                        recordVo.setCorrect("正确");

                    }
                    recordVos.add(recordVo);
                    break;
                case 3:
                    RecordVo recordVo2 = new RecordVo();
                    recordVo2.setGroup_name(record.getGroupTopic().getGroup_name());
                    recordVo2.setTitle(record.getRecord_title());
                    if (record.getRecord_answer().equals("error")) {
                        recordVo2.setAnswer("错误");
                    } else {
                        recordVo2.setAnswer("正确");
                    }

                    recordVo2.setTitle(record.getRecord_title());
                    if (record.getUser_answer().equals("error")) {
                        recordVo2.setUser_answer("错误");
                    } else {
                        recordVo2.setUser_answer("正确");
                    }
                    if (record.getCorrect() == -1) {
                        recordVo2.setCorrect("错误");
                    } else {
                        recordVo2.setCorrect("正确");
                    }
                    recordVos.add(recordVo2);
                    break;
            }
        }
        PageInfo<Record> recordPageInfo = new PageInfo<>(records);
       return new Result<RecordVo>(ResultUtils.SUCCESS, recordVos, recordPageInfo.getTotal());
    }
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Result queryAllPage(Integer page, Integer size, String user_id, String group_topic_id) {
        PageHelper.startPage(page, size);

        List<RecordVo> recordVos = new ArrayList<>();
        List<Record> records = recordDao.queryAllPage(user_id, group_topic_id);
        for (Record record : records) {
            switch (record.getTitle_type()) {
                case 1:
                    RecordVo recordVo1 = new RecordVo();
                    recordVo1.setTitle(record.getRecord_title());
                    recordVo1.setAnswer(record.getRecord_answer());
                    recordVo1.setUser_answer(record.getUser_answer());
                    if (record.getCorrect() == -1) {
                        recordVo1.setCorrect("错误");
                    } else {
                        recordVo1.setCorrect("正确");
                    }
                    recordVos.add(recordVo1);
                    break;
                case 2:
                    RecordVo recordVo = new RecordVo();
                    recordVo.setTitle(record.getRecord_title());
                    String record_answer = record.getRecord_answer();
                    List<String> recordAnswers = JSON.parseObject(record_answer, List.class);
                    StringBuilder stringBuilder = new StringBuilder();
                    for (String str : recordAnswers){
                        stringBuilder.append(str+",");
                    }
                    String s = stringBuilder.toString();
                    recordVo.setAnswer(s.substring(0, s.length()-1));
                    String user_answer = record.getUser_answer();
                    List<String> userAnswer = JSON.parseObject(user_answer, List.class);
                    StringBuilder stringBuilder1 = new StringBuilder();
                    for (String str : userAnswer){
                        stringBuilder1.append(str+",");
                    }
                    String s1 = stringBuilder1.toString();
                    recordVo.setUser_answer(s1.substring(0, s1.length()-1));
                    if (record.getCorrect() == -1) {
                        recordVo.setCorrect("错误");
                    } else {
                        recordVo.setCorrect("正确");

                    }
                    recordVos.add(recordVo);
                    break;
                case 3:
                    RecordVo recordVo2 = new RecordVo();
                    recordVo2.setTitle(record.getRecord_title());
                    if (record.getRecord_answer().equals("error")) {
                        recordVo2.setAnswer("错误");
                    } else {
                        recordVo2.setAnswer("正确");
                    }

                    recordVo2.setTitle(record.getRecord_title());
                    if (record.getUser_answer().equals("error")) {
                        recordVo2.setUser_answer("错误");
                    } else {
                        recordVo2.setUser_answer("正确");
                    }
                    if (record.getCorrect() == -1) {
                        recordVo2.setCorrect("错误");
                    } else {
                        recordVo2.setCorrect("正确");
                    }
                    recordVos.add(recordVo2);
                    break;
            }
        }
        PageInfo<Record> recordPageInfo = new PageInfo<>(records);
        return new Result<RecordVo>(ResultUtils.SUCCESS, recordVos, recordPageInfo.getTotal());
    }
}
