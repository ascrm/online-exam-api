package com.ascrm.converter;

import cn.hutool.core.bean.BeanUtil;
import com.ascrm.entity.User;
import com.ascrm.viewer.UserViewer;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: ascrm
 * @Date: 2025/1/28
 */
@Component
public class UserConverter {

    public List<UserViewer> to(List<User> userList){
        List<UserViewer> list=new ArrayList<>();
        userList.forEach(user->{
            UserViewer userViewer = BeanUtil.copyProperties(user, UserViewer.class);
            userViewer.setCreatedAt(user.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            userViewer.setRole(user.getRole() == null?"未知":user.getRole() == 1 ? "管理员" : "普通用户");
            userViewer.setGender(user.getGender() == null ?"未知": user.getGender() == 1 ? "男" : "女");
            list.add(userViewer);
        });
        return list;
    }
}
