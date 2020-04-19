package com.dati.service;

import com.dati.request.BlankVo;
import com.dati.request.RecordVo;
import com.dati.responseresult.Result;

public interface RecordService {
    Result addRecordSingle(RecordVo recordVo);
    Result addRecordBlank(BlankVo blankVo);
    Result queryPage(Integer page, Integer size, String user_id, String group_topic_id, String title);
    Result queryAllPage(Integer page, Integer size, String user_id, String group_topic_id);
}
