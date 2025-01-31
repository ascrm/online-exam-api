package com.ascrm.controller;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.RandomUtil;
import com.ascrm.entity.Result;
import com.ascrm.entity.SmsEntity;
import com.ascrm.entity.User;
import com.ascrm.utils.Sample;
import com.ascrm.utils.UserHolder;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.RequiredArgsConstructor;
import com.ascrm.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.ascrm.entity.table.UserTableDef.USER;


/**
 * 控制层。
 *
 * @author ascrm
 * @since 1.0
 */
@RestController
@RequestMapping("/online/exam")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    //创建本地缓存
    public static Cache<String,String> cache=Caffeine.newBuilder()
            .initialCapacity(10)//初始容量
            .maximumSize(1000)//最大容量
            .expireAfterWrite(2,TimeUnit.MINUTES)//过期时间
            .build();

    /**
     * 用户登录
     */
    @PostMapping("/login")
    public Result<Map<String,Object>> login(@RequestBody User user) {
        User userResult = userService.queryChain()
                .select(USER.ALL_COLUMNS)
                .from(USER)
                .where(USER.USERNAME.eq(user.getUsername()))
                .and(USER.IS_DELETE.eq(0))
                .one();
        if(userResult==null || !user.getPassword().equals(userResult.getPassword())){
            return Result.fail("账号或密码错误");
        }
        StpUtil.login(user.getUsername());
        String token = StpUtil.getTokenInfo().getTokenValue() + ";" + userResult.getRole();
        Map<String, Object> map=new HashMap<>();
        map.put("token",token);
        map.put("user",userResult.getNickName()==null?userResult.getUsername():userResult.getNickName());
        return Result.success(map);
    }

    /**
     * 退出登录
     */
    @PostMapping("/logout")
    public Result<String> logout(){
        StpUtil.logout();
        return Result.success();
    }

    /**
     * 发送短信验证码
     */
    @GetMapping("/code")
    public Result<String> sendSms(String phoneNumber){

        String code = RandomUtil.randomNumbers(6);

        SmsEntity smsEntity = new SmsEntity();
        smsEntity.setSignName("")
                .setPhoneNumber(phoneNumber)
                .setTemplateCode("")
                .setTemplateParam(code);
        Sample.sendSms(smsEntity);
        cache.put(UserHolder.getUsername(),code);
        return Result.success("发送短信成功",code);
    }

    /**
     * 修改密码
     */
    @PutMapping("/password")
    public Result<String> updatePassword(String code,String newPassword){
        String cacheCode = cache.getIfPresent(UserHolder.getUsername());
        if(!code.equals(cacheCode)){
            return Result.fail("验证码错误");
        }
        String password = SaSecureUtil.md5(newPassword);
        userService.updateChain().set(USER.PASSWORD,password)
                .where(USER.USERNAME.eq(UserHolder.getUsername()))
                .update();
        return Result.success("修改密码成功");
    }
}