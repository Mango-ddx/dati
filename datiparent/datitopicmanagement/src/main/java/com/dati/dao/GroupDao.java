package com.dati.dao;

import com.dati.pojo.GroupTopic;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface GroupDao {
    @Select("select id, group_name, class_id from group_topic where class_id = #{id} ")
    List<GroupTopic> findById(String id);

    @Insert("insert into group_topic(id, group_name, class_id) values (#{id}, #{group_name}, #{class_id})")
    int addGroup(GroupTopic groupTopic);

    @Select("select count(1) from group_topic where group_name = #{name}")
    int findByName(String name);

    @Select("select count(1) from group_topic where id != #{id} and group_name = #{group_name}")
    int findByNotId(GroupTopic groupTopic);

    @Update("update group_topic set group_name = #{group_name} where id = #{id}")
    int update(GroupTopic groupTopic);

    @Delete("delete from group_topic where id = #{id}")
    int delete(String id);

    @Select("select class_id from group_topic where id = #{id}")
    String class_id (String id);

    @Select("select count(1) from Topics where group_topic_id = #{id}")
    int queryTopic(String id);

    @Select("select count(1) from group_topic where id = #{id}")
    int queryGroup(String id);

}
