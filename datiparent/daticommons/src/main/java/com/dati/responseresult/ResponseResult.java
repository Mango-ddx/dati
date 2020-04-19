package com.dati.responseresult;

import java.io.Serializable;

public interface  ResponseResult extends Serializable {
    int code();
    String message();
    boolean success();
}
