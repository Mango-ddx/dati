package com.dati.controller;

import com.dati.Utils.ExcepUtils;
import com.dati.Utils.ResultUtils;
import com.dati.exception.MyException;
import com.dati.pojo.User;
import com.dati.request.MyRequestBody;
import com.dati.responseresult.ResponseResult;
import com.dati.responseresult.Result;
import com.dati.responseresult.UserResponse;
import com.dati.service.UserClassService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

@RestController
@RequestMapping("/login")
//配置默认的降级方法，（返回值必须保持一致），配置发生任何异常都不触发降级（交给全局异常处理器处理）
@DefaultProperties(defaultFallback = "defaultFall", ignoreExceptions = {Throwable.class})
public class UserController {
    @Autowired
    private UserClassService userClassService;
    @GetMapping("/classes")
    @HystrixCommand
    public Result queryAllClass(){
        return userClassService.findAll();
    }
//注册
    @PostMapping("/user")
    @HystrixCommand
    public Result addOneUser (@RequestBody MyRequestBody body){
        return userClassService.addUser(body);
    }
//登录
    @GetMapping("/user/{username}")
    @HystrixCommand(fallbackMethod = "login")
    public UserResponse userResponse(@PathVariable("username")String username,
                                        @RequestParam(value = "password") String password){
       return userClassService.login(username, password);
    }
    public Result defaultFall(){
         return new Result(ResultUtils.TIMEOUT_ERROR);
    }
//登录api独占的降级方法。（返回值和形参必须保持一致）
    public UserResponse login(String username, String password){
        return new UserResponse(500, "网络开小差了,请稍后重新尝试。", false);
    }

}
