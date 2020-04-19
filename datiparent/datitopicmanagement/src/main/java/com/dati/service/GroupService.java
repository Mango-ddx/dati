package com.dati.service;

import com.dati.pojo.GroupTopic;
import com.dati.responseresult.Result;

public interface GroupService  {
     Result<GroupTopic> findById(String id);
     Result addGroup(GroupTopic groupTopic);
     Result update(GroupTopic groupTopic);
     Result delete(String id);
}
