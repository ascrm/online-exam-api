package com.ascrm.mapper;

import com.ascrm.entity.User;
import com.mybatisflex.core.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 映射层。
 *
 * @author ascrm
 * @since 1.0
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
