package com.ascrm.controller;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.StpUtil;
import com.ascrm.entity.Result;
import com.ascrm.entity.User;
import lombok.RequiredArgsConstructor;
import com.ascrm.service.UserService;
import org.springframework.web.bind.annotation.*;

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
public class UserController {

    private final UserService userService;

    /**
     * 用户登录
     */
    @PostMapping("/login")
    public Result<String> login(@RequestBody User user) {
        User userResult = userService.queryChain()
                .select(USER.ALL_COLUMNS)
                .from(USER)
                .where(USER.USERNAME.eq(user.getUsername()))
                .one();
        if(!user.getPassword().equals(userResult.getPassword())){
            return Result.fail("账号或密码错误");
        }
        StpUtil.login(user.getUsername());
        return Result.success("登录成功", StpUtil.getTokenInfo().getTokenValue()+";"+userResult.getPermission());
    }

    /**
     * 退出登录
     */
    @PostMapping("/logout")
    public Result<String> logout(){
        StpUtil.logout();
        return Result.success("退出登录成功");
    }


    /**
     * 新增用户
     */
    @PostMapping("/user")
    public Result<String> add(@RequestBody User user) {
        user.setPassword(SaSecureUtil.md5(user.getPassword()));
        userService.save(user);
        return Result.success("新增成功");
    }

    /**
     * 修改用户信息
     */
    @PutMapping("/user")
    public Result<String> update(@RequestBody User user) {
        userService.updateById(user,true);
        return Result.success("修改成功");
    }

    /**
     * 删除用户
     */
    @DeleteMapping("/user/{id}")
    public Result<String> delete(@PathVariable Long id) {
        userService.updateChain()
                .set(USER.IS_DELETE, true)
                .where(USER.ID.eq(id))
                .update();
        return Result.success("删除成功");
    }
}