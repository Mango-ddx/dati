package com.dati.responseresult;


public class ResultOne<T> implements ResponseResult{
    private int code;
    private String message;
    private boolean success;
    private T result;

    public ResultOne(ResponseResult responseResult, T t){
        this.result = t;
        this.code = responseResult.code();
        this.message = responseResult.message();
        this.success = responseResult.success();
    }

    public ResultOne(){}

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

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
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
