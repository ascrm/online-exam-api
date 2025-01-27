package com.ascrm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {
    private int code;
  
    private String msg;  
  
    private T data;

    public static <T> Result<T> success(){
        return new Result<>(200,null,null);
    }
  
    public static <T> Result<T> success(String msg){
        return new Result<>(200,msg,null);
    }
  
    public static <T> Result<T> success(String msg,T data){
        return new Result<>(200,msg,data);
    }
  
    public static <T> Result<T> fail(String msg){
        return new Result<>(0,msg,null);
    }
}