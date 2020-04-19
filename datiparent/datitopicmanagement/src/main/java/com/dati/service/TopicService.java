package com.dati.service;

import com.dati.request.BlankVo;
import com.dati.request.JudgmentVo;
import com.dati.request.SingleVo;
import com.dati.pojo.Topics;
import com.dati.responseresult.Result;
import com.dati.responseresult.ResultOne;

public interface TopicService {
    Result addSingle(SingleVo singleVo);
    Result addBlank(BlankVo blankVo);
    Result addJudgment(JudgmentVo judgmentVo);
    ResultOne randomQuery(String user_id, String class_id);
}
