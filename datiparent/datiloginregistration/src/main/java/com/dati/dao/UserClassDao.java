package com.dati.dao;

import com.dati.pojo.Permission;
import com.dati.pojo.Role;
import com.dati.pojo.User;
import com.dati.request.MyRequestBody;
import com.dati.pojo.UserClass;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserClassDao {
    //查询所有班级
    @Select("select id, class_name from user_class")
    List<UserClass> findAll();
    //添加用户
    @Insert("insert into user(id, username, password, account, class_id) values (#{id},#{username},#{password},#{account},#{class_id})")
    int addUser(MyRequestBody myRequestBody);
    //根据账号或者用户名进行统计。(判断是否存在)
    @Select("select count(1) from user where username = #{username} or account = #{account}")
    int findUserByUsernameOrAccount(@Param("username") String username,@Param("account") String account);
    //根据id查询班级是否存在。
    @Select("select count(1) from user_class where id = #{id}")
    int countById(String id);

    //查询用户的id，和用的角色以及权限
    User findByIdUsernameOrAccount(String account);

    //根据用户id查询角色
    List<Role> findByUser_id(String user_id);

    //查询权限
    @Select("select p.id, p.permission_name from permission as p inner join permission_role as pr " +
            "on pr.role_id = #{role_id} and pr.permission_id = p.id")
    List<Permission> findByRole_id(String role_id);
}