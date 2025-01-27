package com.ascrm.service.impl;


import org.springframework.stereotype.Service;
import com.ascrm.service.UserService;
import com.ascrm.entity.User;
import com.ascrm.mapper.UserMapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;

/**
 * 服务层实现。
 *
 * @author ascrm
 * @since 1.0
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}