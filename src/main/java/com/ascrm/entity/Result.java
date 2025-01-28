package com.ascrm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {
    private int code;
  
    private String msg;  
  
    private T data;

    public static <T> Result<T> success(){
        return new Result<>(200,"true",null);
    }
  
    public static <T> Result<T> success(T data){
        return new Result<>(200,"true",data);
    }
  
    public static <T> Result<T> success(String msg,T data){
        return new Result<>(200,msg,data);
    }
  
    public static <T> Result<T> fail(String msg){
        return new Result<>(0,msg,null);
    }
}