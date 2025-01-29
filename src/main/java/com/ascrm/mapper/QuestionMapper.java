package com.ascrm.mapper;

import com.ascrm.entity.Question;
import com.mybatisflex.core.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 映射层。
 *
 * @author ascrm
 * @since 1.0
 */
@Mapper
public interface QuestionMapper extends BaseMapper<Question> {
}
