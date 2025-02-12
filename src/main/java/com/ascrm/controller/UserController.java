package com.ascrm.controller;

import cn.dev33.satoken.secure.SaSecureUtil;
import com.ascrm.converter.UserConverter;
import com.ascrm.entity.DTO.UserDTO;
import com.ascrm.entity.ExamPaper;
import com.ascrm.entity.PageResult;
import com.ascrm.entity.Result;
import com.ascrm.entity.User;
import com.ascrm.service.UserService;
import com.ascrm.utils.UserHolder;
import com.ascrm.viewer.UserViewer;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

import static com.ascrm.entity.table.UserTableDef.USER;

/**
 * @Author: ascrm
 * @Date: 2025/1/28
 */
@RestController
@RequestMapping("/online/exam")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final UserConverter userConverter;

    /**
     * 分页条件查询用户
     */
    @PostMapping("/users")
    private Result<PageResult<UserViewer>> getUsers(@RequestBody UserDTO userDTO){
        Page<User> page = userService.page(new Page<>(userDTO.getPageNum(), userDTO.getPageSize()), new QueryWrapper()
                .where(USER.IS_DELETE.eq(0))
                .and(USER.USERNAME.eq(userDTO.getUsername()))
                .and(USER.NICK_NAME.like(userDTO.getNickName()))
                .and(USER.EMAIL.eq(userDTO.getEmail()))
                .and(USER.PHONE.eq(userDTO.getPhone()))
        );
        PageResult<UserViewer> pageResult = new PageResult<>();
        pageResult.setPageSize(userDTO.getPageSize())
                .setPageNum(userDTO.getPageNum())
                .setTotal(page.getTotalRow())
                .setList(userConverter.to(page.getRecords()));
        return Result.success(pageResult);
    }

    /**
     * 新增管理员
     */
    @PostMapping("/user")
    public Result<String> addUser(@RequestBody User user) {
        //默认密码
        user.setPassword(SaSecureUtil.md5("123456")).setRole(1).setCreatedBy(UserHolder.getUsername()).setIsDelete(0);
        userService.save(user);
        return Result.success();
    }

    /**
     * 重置密码
     */
    @PutMapping("/user")
    public Result<String> resetPassword(@RequestBody ExamPaper examPaper) {
        userService.updateChain()
                .set(USER.PASSWORD, SaSecureUtil.md5("123456"))
                .where(USER.ID.eq(examPaper.getId()))
                .update();
        return Result.success();
    }

    /**
     * 删除用户
     */
    @DeleteMapping("/user")
    public Result<String> deleteUser(int id) {
        userService.updateChain()
                .set(USER.IS_DELETE, true)
                .where(USER.ID.eq(id))
                .update();
        return Result.success();
    }

    /**
     * 批量删除用户
     */
    @DeleteMapping("/users")
    public Result<String> deleteUsers(String ids) {
        userService.updateChain()
                .set(USER.IS_DELETE, true)
                .where(USER.ID.in(Arrays.asList(ids.split(","))))
                .update();
        return Result.success();
    }
}
