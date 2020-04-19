package com.dati.Utils;

import com.dati.exception.MyException;
import com.dati.exception.UserExcepResponse;
import com.dati.responseresult.ResponseResult;

public class ExcepUtils {
     public static void createException(ResponseResult responseResult){
         throw new MyException(responseResult);
     }
}
