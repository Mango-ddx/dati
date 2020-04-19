package com.dati.exception;

import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;


//@RestControllerAdvice
public class ExceptionEnhance {
    private Logger logger = LoggerFactory.getLogger(ExceptionEnhance.class);
    private static Map<Class<? extends Throwable>, UserExcepResponse> map = new HashMap();
    static {
        //添加可以预知的异常
        map.put(UnauthorizedException.class, new UserExcepResponse(500, "你角色的权限不足，请联系管理员分配角色。。",false));
    }
    //处理手动抛出的异常
    @ExceptionHandler(value = MyException.class)
    public UserExcepResponse userExcepResponse(MyException e){
         logger.error("catch myException exception is :{}",e.getResponse().getMessage());
         return e.getResponse();
    }
    //处理可预知和不可预知的异常
    @ExceptionHandler(value = Throwable.class)
    public UserExcepResponse excepResponse(Throwable e){
        UserExcepResponse userExcepResponse = map.get(e.getClass());
        //如果可以拿到证明是可以预知到的异常。
        if (userExcepResponse != null) {
            logger.error("catch Throwable exception is :{}",userExcepResponse.getMessage());
            return map.get(e.getClass());
        }
        logger.error("catch Throwable exception is :{}", "发生不明确异常");
        logger.error("不明确异常详细信息：{}", e.getMessage());
        //如果找不到，证明是不可以预知的异常。
        return new UserExcepResponse(500,"服务器太过拥挤，请稍后重试。", false);
    }
}
