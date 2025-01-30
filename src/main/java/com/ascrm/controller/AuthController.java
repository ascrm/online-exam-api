package com.ascrm.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.ascrm.entity.Result;
import com.ascrm.entity.User;
import lombok.RequiredArgsConstructor;
import com.ascrm.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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
        if(!user.getPassword().equals(userResult.getPassword())){
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
}