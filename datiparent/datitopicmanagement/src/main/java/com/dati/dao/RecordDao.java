package com.dati.dao;

import com.dati.pojo.GroupTopic;
import com.dati.pojo.Record;
import com.dati.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RecordDao {
    @Insert("insert into record(record_title, record_answer, user_answer, correct, user_id, group_topic_id,  topics_id," +
            " title_type) values(#{record_title}, #{record_answer}, #{user_answer}, #{correct}, #{user_id},  #{group_topic_id}," +
            "#{topics_id}, #{title_type})")
    int addRecordSingle(Record record);

    @Select("select count(1) from user where id = #{id}")
    int QueryUser(String id);

    @Select("select count(1) from record where record_title = #{record_title} and user_id = #{user_id} " +
            "and group_topic_id = #{group_topic_id}")
    int queryRecord(Record record);

    List<Record> queryPage(@Param("user_id") String user_id, @Param("group_topic_id")String group_topic_id, @Param("title") String title);
    @Select("select record_title, record_answer, user_answer, correct, title_type from record where user_id = #{user_id} and group_topic_id = #{group_topic_id}")
    List<Record> queryAllPage(@Param("user_id") String user_id, @Param("group_topic_id") String group_topic_id);
}
