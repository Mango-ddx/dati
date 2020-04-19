package com.dati.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.crazycake.shiro.AuthCachePrincipal;

import java.io.Serializable;
import java.util.List;

/**
 * 用户表
 * 必须实现才可以放入redis中（AuthCachePrincipal）
 * */
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class User implements AuthCachePrincipal, Serializable {
    private String id;
    private String username;
    private String account;
    private String password;
    private String creationdate;
    private String class_id;
    private List<Role> roles;

    @Override
    public String getAuthCacheKey() {
        return null;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCreationdate() {
        return creationdate;
    }

    public void setCreationdate(String creationdate) {
        this.creationdate = creationdate;
    }

    public String getClass_id() {
        return class_id;
    }

    public void setClass_id(String class_id) {
        this.class_id = class_id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", creationdate='" + creationdate + '\'' +
                ", class_id='" + class_id + '\'' +
                ", roles=" + roles +
                '}';
    }
}
