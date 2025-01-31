package com.ascrm.interceptor;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.http.HttpStatus;
import com.alibaba.fastjson.JSON;
import com.ascrm.entity.Result;
import com.ascrm.utils.UserHolder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @Author: ascrm
 * @Date: 2025/1/31
 */
@Component
@Slf4j
public class WebMvcInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
      log.info("http-requestUrl: {}",request.getRequestURL());

      String token=request.getHeader("Authorization");
      if(token==null){
          String msg = JSON.toJSONString(new Result<>(HttpStatus.HTTP_UNAUTHORIZED,"登录失效，没有访问权限",null));
          response.setStatus(HttpStatus.HTTP_UNAUTHORIZED);
          response.getWriter().write(msg);
          return false;
      }

      if(token.equals(StpUtil.getTokenValue())){
          String msg = JSON.toJSONString(new Result<>(HttpStatus.HTTP_UNAUTHORIZED,"登录失效，没有访问权限",null));
          response.setStatus(HttpStatus.HTTP_UNAUTHORIZED);
          response.getWriter().write(msg);
          return false;
      }
      UserHolder.set("username",StpUtil.getLoginId());
      return true;
    }

}
