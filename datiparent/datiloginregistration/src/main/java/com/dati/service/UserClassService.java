package com.dati.service;

import com.dati.pojo.User;
import com.dati.request.MyRequestBody;
import com.dati.responseresult.Result;
import com.dati.responseresult.UserResponse;

public interface UserClassService {
    Result findAll();
    Result addUser(MyRequestBody myRequestBody);
    User queryUser(String account);
    UserResponse login(String username, String password);
}
