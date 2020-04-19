package com.dati.responseresult;

import com.dati.pojo.User;

import java.util.Set;

public class UserResponse implements ResponseResult {
    private int code;
    private String message;
    private boolean success;
    private User user;
    private String token;
    private Set<String> per_id;
    public UserResponse(int code, String message, boolean success, User user, String token) {
        this.code = code;
        this.message = message;
        this.success = success;
        this.user = user;
        this.token = token;
    }

    public UserResponse(int code, String message, boolean success) {
        this.code = code;
        this.message = message;
        this.success = success;
        this.user = user;
        this.token = token;
    }
    public UserResponse(){}

    public Set<String> getper_id() {
        return per_id;
    }

    public void setper_id(Set<String> per_id) {
        this.per_id = per_id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }

    @Override
    public boolean success() {
        return success;
    }
}
