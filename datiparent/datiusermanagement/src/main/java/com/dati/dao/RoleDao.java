package com.dati.dao;

import com.dati.pojo.Role;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleDao {
    @Select("SELECT r.id,r.role_name,r.prompt FROM role AS r WHERE r.`id` NOT IN(SELECT ru.`role_id` FROM role_user AS ru WHERE ru.user_id = #{userId})")
    List<Role> queryRole(String userId);

    @Insert("insert into role_user(role_id, user_id) values(#{role_id}, #{user_id})")
    int addRole(@Param("role_id") String role_id, @Param("user_id") String user_id);

    @Select("select count(1) from role where id = #{id}")
    int findById(String id);

    @Select("select count(1) from role_user where role_id = #{role_id} and user_id = #{user_id}")
    int isRole(@Param("role_id") String role_id, @Param("user_id") String user_id);

    @Select("SELECT r.id, r.role_name, r.prompt FROM role_user AS ru INNER JOIN role AS r ON ru.user_id = #{user_id} AND r.id = ru.`role_id`")
    List<Role> queryRole1(String user_id);

    @Delete("delete from role_user where role_id = #{role_id} and user_id = #{user_id}")
    int deleteRole(@Param("role_id") String role_id, @Param("user_id") String user_id);
}
