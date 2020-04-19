package com.dati.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;
//加上为空的时候可以不报错
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class Role implements Serializable {
    private String id;
    private String role_name;
    private String prompt;
    private List<Permission> permissions;


    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id='" + id + '\'' +
                ", role_name='" + role_name + '\'' +
                ", prompt='" + prompt + '\'' +
                ", permissions=" + permissions +
                '}';
    }
}
