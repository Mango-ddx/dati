package com.dati.service;

import com.alibaba.fastjson.JSON;
import com.dati.Utils.Md5Utils;
import com.dati.pojo.User;
import com.dati.request.MyRequestBody;
import com.dati.Utils.ExcepUtils;
import com.dati.dao.UserClassDao;
import com.dati.pojo.UserClass;
import com.dati.Utils.ResultUtils;
import com.dati.responseresult.Result;
import com.dati.responseresult.UserResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class UserClassServiceImpl implements UserClassService {
    @Autowired
    UserClassDao userClassDao;
    @Autowired
    StringRedisTemplate redisTemplate;
    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Result findAll() {
        String classes = redisTemplate.opsForValue().get("classes");
        if (classes != null) {
            List<UserClass> list = JSON.parseObject(classes, List.class);
            return new Result<UserClass>(ResultUtils.QUERY_WAS_SUCCESS, list);
        }
        List<UserClass> all = userClassDao.findAll();
        String s = JSON.toJSONString(all);
        redisTemplate.opsForValue().set("classes", s, 24, TimeUnit.HOURS);
        return new Result<UserClass>(ResultUtils.QUERY_WAS_SUCCESS, all);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    //添加用户
    public Result addUser(MyRequestBody myRequestBody) {
        if (StringUtils.isEmpty(myRequestBody.getUsername())) {
            ExcepUtils.createException(ResultUtils.USERNAME_NOLL);
        }

        if (StringUtils.isEmpty(myRequestBody.getAccount())) {
            ExcepUtils.createException(ResultUtils.ACCOUNT_NOLL);
        }

        if (StringUtils.isEmpty(myRequestBody.getPassword())) {
            ExcepUtils.createException(ResultUtils.PASSWORD_NOLL);
        }

        if (StringUtils.isEmpty(myRequestBody.getClass_id())) {
            ExcepUtils.createException(ResultUtils.CLASS_NOLL);
        }

        if (userClassDao.findUserByUsernameOrAccount(myRequestBody.getUsername(), myRequestBody.getAccount()) > 0) {
            ExcepUtils.createException(ResultUtils.ACCOUNT_USERNAME);
        }

        if (userClassDao.countById(myRequestBody.getClass_id()) < 1) {
            ExcepUtils.createException(ResultUtils.QUERY_CLASS_NULL);
        }

        String replace = UUID.randomUUID().toString().replace("-", "");
        myRequestBody.setId(replace);
        //MD5加密密码，登录时，要用同样规则进行加密然后进行判断。
        String md5Password = Md5Utils.toMd5Password(myRequestBody.getPassword(), myRequestBody.getAccount());
        myRequestBody.setPassword(md5Password);
        int i = userClassDao.addUser(myRequestBody);
        //班级存在进行添加，否则报错。
        if (i > 0) {
            return new Result(ResultUtils.ADD_USER_SUCCESS);
        }
        return new Result(ResultUtils.ADD_USER_ERROR);
    }


    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public User queryUser(String account) {
        //String md5Password = Md5Utils.toMd5Password(user.getPassword(), user.getAccount());
        User byIdUsernameOrAccount = userClassDao.findByIdUsernameOrAccount(account);
        return byIdUsernameOrAccount;
    }


    @Override
    public UserResponse login(String username, String password) {
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        //如果之前登录过先把之前的登录状态退出。
        if (subject.isAuthenticated()) {
            subject.logout();
        }
        try {
            subject.login(usernamePasswordToken);
        } catch (UnknownAccountException e) {
            ExcepUtils.createException(ResultUtils.USERNAME_NO);
        } catch (IncorrectCredentialsException i){
            ExcepUtils.createException(ResultUtils.PASSWORD_ERROR);
        }
        User user = (User) subject.getPrincipals().getPrimaryPrincipal();
        subject.getSession().setTimeout(60 * 1000 * 60); //一个小时失效（和分布式redis缓存时间保持一致）
        Serializable id = subject.getSession().getId();
        User user1 = new User();
        user1.setId(user.getId());
        user1.setUsername(user.getUsername());
        user1.setClass_id(user.getClass_id());
        UserResponse userResponse = new UserResponse(200, "登录成功", true, user1, id.toString());
        return userResponse;
    }

}
