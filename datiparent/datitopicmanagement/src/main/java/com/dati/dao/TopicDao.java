package com.dati.dao;

import com.dati.pojo.Record;
import com.dati.pojo.Topics;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicDao {
    @Insert("insert into topics(title, option_ans, answer, title_type, group_topic_id) values("+
            " #{title}, #{option_ans}, #{answer}, #{title_type}, #{group_topic_id})")
    int addSingle(Topics topics);

    @Insert("insert into topics (title, answer, title_type, group_topic_id) values (#{title}," +
            " #{answer},#{title_type},#{group_topic_id})")
    int addBlank(Topics topics);

    @Insert("insert into topics (title, option_ans, answer, title_type, group_topic_id) values (#{title}," +
            "#{option_ans}, #{answer},#{title_type},#{group_topic_id})")
    int addJudgment(Topics topics);

    //过滤并且随机查询一条记录。
    @Select("SELECT t.id, t.title, t.answer, t.option_ans, t.title_type, t.group_topic_id FROM topics as t WHERE " +
            "group_topic_id = #{group_id} AND id NOT IN(SELECT r.topics_id FROM record AS r WHERE r.user_id = #{user_id})" +
            "LIMIT 1")
    Topics randomQuery(@Param("group_id") String group_id, @Param("user_id") String user_id);
    @Select("select answer from topics where id = #{id}")
    Topics findById(Integer id);

    @Select("select count(1) from topics where group_topic_id = #{group_id} and title = #{title}")
    int findByGroupIdAndTitle(String group_id, String title);
}
