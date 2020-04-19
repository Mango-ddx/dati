package com.dati.config;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
public class ShiroConfig {
    //安全管理器，shiro核心
    public SecurityManager securityManager (AuthorizingRealm authorizingRealm){
        DefaultWebSecurityManager defaultSecurityManager = new DefaultWebSecurityManager();
        defaultSecurityManager.setCacheManager(cacheManager());
        defaultSecurityManager.setRealm(authorizingRealm);
        defaultSecurityManager.setSessionManager(defaultWebSessionManager());
        return defaultSecurityManager;
    }

    public SecurityManager securityManager (){
        DefaultWebSecurityManager defaultSecurityManager = new DefaultWebSecurityManager();
        defaultSecurityManager.setCacheManager(cacheManager());
        defaultSecurityManager.setSessionManager(defaultWebSessionManager());
        return defaultSecurityManager;
    }

    public ShiroFilterFactoryBean filterFactoryBean(){
         ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
         return shiroFilterFactoryBean;
    }
    @Bean
    //开启对注解的支持
    public AuthorizationAttributeSourceAdvisor sourceAdvisor (SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorization = new AuthorizationAttributeSourceAdvisor();
        authorization.setSecurityManager(securityManager);
        return authorization;
    }
    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private Integer port;
    @Bean
    public RedisManager redisManager(){
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(host);
        redisManager.setPort(port);
        return redisManager;
    }
    @Bean
    public RedisSessionDAO dao (){
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        //redis失效时间，单位秒。（最好和session时间保存一致。）
        redisSessionDAO.setExpire(3600);
        redisSessionDAO.setRedisManager(redisManager());
        return redisSessionDAO;
    }
    @Bean
    public DefaultWebSessionManager defaultWebSessionManager (){
        MySessionManage mySessionManage = new MySessionManage();
        mySessionManage.setSessionDAO(dao());
        mySessionManage.setCacheManager(cacheManager());
        return mySessionManage;
    }
    @Bean
    public RedisCacheManager cacheManager (){
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        return redisCacheManager;
    }

}
