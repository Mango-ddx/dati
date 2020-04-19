package com.dati.exception;

import com.dati.responseresult.ResponseResult;

public class MyException extends RuntimeException {
     private UserExcepResponse response;
     public MyException(ResponseResult userExcepResponse){
         response = new UserExcepResponse(userExcepResponse.code(),
                 userExcepResponse.message(), userExcepResponse.success());
     }

    public UserExcepResponse getResponse() {
        return response;
    }

    public void setResponse(UserExcepResponse response) {
        this.response = response;
    }
}
