package com.dati.config;

import com.dati.realm.AuthRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfigLogin  extends ShiroConfig{
    @Bean
    public SecurityManager securityManager(AuthorizingRealm realm) {
        return super.securityManager(realm);
    }

    @Bean
    public ShiroFilterFactoryBean filterFactoryBean(SecurityManager securityManager) {
         ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
         shiroFilterFactoryBean.setSecurityManager(securityManager);
         Map<String, String> map = new LinkedHashMap<>();
         map.put("/**", "anon");
         shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
         return shiroFilterFactoryBean;
    }
}
