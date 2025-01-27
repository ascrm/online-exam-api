package com.ascrm.utils;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class UserHolder {
    //创建一个ThreadLocal，其中存的是map类型的数据  
    private static final InheritableThreadLocal<Map<String, Object>> THREAD_LOCAL
            = new InheritableThreadLocal<>();  
  
    //设置值  
    public static void set(String key, Object val) {  
        Map<String, Object> map = getThreadLocalMap();  
        map.put(key, val);  
    }  
  
    //获取值  
    public static Object get(String key){  
        Map<String, Object> threadLocalMap = getThreadLocalMap();  
        return threadLocalMap.get(key);  
    }  

    public static void remove(){  
        THREAD_LOCAL.remove();
    }

    public static String getUsername(){
        return (String) getThreadLocalMap().get("username");
    } 
    
    //获取ThreadLocal中的map，若map为空，则创建一个；若不为空，则使用之前的那个  
    public static Map<String,Object> getThreadLocalMap(){  
        Map<String, Object> map = THREAD_LOCAL.get();  
        if(Objects.isNull(map)){
            map=new ConcurrentHashMap<>();
            THREAD_LOCAL.set(map);  
        }  
        return map;  
    }  
}