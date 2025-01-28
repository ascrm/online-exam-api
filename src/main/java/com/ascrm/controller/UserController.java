package com.ascrm.controller;

import cn.dev33.satoken.secure.SaSecureUtil;
import com.ascrm.converter.UserConverter;
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
        return Result.success("查询成功",pageResult);
    }

    /**
     * 新增管理员
     */
    @PostMapping("/user")
    public Result<String> addUser(@RequestBody User user) {
        //默认密码
        user.setPassword(SaSecureUtil.md5("123456")).setRole(1).setCreatedBy(UserHolder.getUsername()).setIsDelete(0);
        userService.save(user);
        return Result.success("新增成功");
    }

    /**
     * 重置密码
     */
    @PutMapping("/user/{id}")
    public Result<String> resetPassword(@PathVariable int id) {
        userService.updateChain()
                .set(USER.PASSWORD, SaSecureUtil.md5("123456"))
                .where(USER.ID.eq(id))
                .update();
        return Result.success("重置成功");
    }

    /**
     * 修改用户信息
     */
    @PutMapping("/user")
    public Result<String> updateUser(@RequestBody User user) {
        userService.updateById(user,true);
        return Result.success("修改成功");
    }

    /**
     * 删除用户
     */
    @DeleteMapping("/user/{id}")
    public Result<String> deleteUser(@PathVariable Long id) {
        userService.updateChain()
                .set(USER.IS_DELETE, true)
                .where(USER.ID.eq(id))
                .update();
        return Result.success("删除成功");
    }
}
