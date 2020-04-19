package com.dati.config;

import com.dati.realm.AuthRealm;
import com.dati.shiroFilter.AuthenticatFilter;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;
@Configuration
public class ShiroConfigTopic extends ShiroConfig {
    @Bean
    public SecurityManager securityManager() {
        return super.securityManager(new AuthRealm());
    }

    @Bean
    public ShiroFilterFactoryBean filterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, String> map = new LinkedHashMap<>();
        Map<String, Filter> map1 = new LinkedHashMap<>();
        map1.put("Authen", new AuthenticatFilter());
        shiroFilterFactoryBean.setFilters(map1);
        map.put("/**", "Authen");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }
}
