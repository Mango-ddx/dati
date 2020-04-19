package com.dati.test;

import com.alibaba.fastjson.JSON;
import com.dati.LoginApplication;
import com.dati.pojo.User;
import com.dati.service.UserClassService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;
import java.util.UUID;

@RunWith(value = SpringRunner.class)
@SpringBootTest(classes = LoginApplication.class)
public class UserClassTest {
    @Autowired
    private UserClassService userClassService;
    @Autowired
    StringRedisTemplate template;
    @Test
    public void findAll(){
        System.out.println(StringRedisTemplate.class == template.getClass());
    }
    @Test
    public void findUser(){
        System.out.println(UUID.randomUUID().toString().replace("-", ""));

    }

    public static void main(String[] args) {
        System.out.println(UUID.randomUUID().toString().replace("-", ""));
    }
}
