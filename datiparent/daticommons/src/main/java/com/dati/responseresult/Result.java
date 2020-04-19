package com.dati.responseresult;
import java.util.List;

public class Result<T> implements ResponseResult {
     private int code;
     private String message;
     private boolean success;
     private List<T> results;
     private Long total;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Result(ResponseResult responseResult, List<T> userClasses){
           this.results = userClasses;
           this.code = responseResult.code();
           this.message = responseResult.message();
           this.success = responseResult.success();
     }

    public Result(ResponseResult responseResult, List<T> userClasses, Long total){
        this.total = total;
        this.results = userClasses;
        this.code = responseResult.code();
        this.message = responseResult.message();
        this.success = responseResult.success();
    }

    public Result(ResponseResult responseResult){
        this.code = responseResult.code();
        this.message = responseResult.message();
        this.success = responseResult.success();
    }

     public Result(){
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

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", success=" + success +
                ", userClasses=" + results +
                '}';
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

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }
}
