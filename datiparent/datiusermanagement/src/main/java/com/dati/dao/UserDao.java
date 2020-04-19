package com.dati.dao;

import com.dati.pojo.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {
       @Select("select id, username from user where class_id = #{class_id}")
    List<User> findByClass_id(String class_id);

    @Select("select id, username from user where class_id = #{class_id} and id != #{id}")
    List<User> findByClass_idAndNotId(String class_id, String id);
    @Select("select count(1) from user where id = #{id}")
    int findById(String id);
}
