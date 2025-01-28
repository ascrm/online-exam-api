package com.ascrm.controller;

import cn.dev33.satoken.secure.SaSecureUtil;
import com.ascrm.converter.UserConverter;
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
import java.util.List;

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
     * 分页查询用户
     */
    @GetMapping("/users")
    private Result<PageResult<UserViewer>> getUsers(@RequestParam("pageNum") int pageNum,
                                              @RequestParam("pageSize") int pageSize){
        Page<User> page = userService.page(new Page<>(pageNum, pageSize),new QueryWrapper()
                .where(USER.IS_DELETE.eq(0)));
        PageResult<UserViewer> pageResult = new PageResult<>();
        pageResult.setPageSize(pageSize)
                .setPageNum(pageNum)
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
    public Result<String> deleteUsers(String idsStr) {
        List<String> ids = Arrays.asList(idsStr.split(","));
        List<User> users = userService.getMapper().selectListByIds(ids);
        users.forEach(user->user.setIsDelete(1));
        userService.updateBatch(users);
        return Result.success();
    }
}
