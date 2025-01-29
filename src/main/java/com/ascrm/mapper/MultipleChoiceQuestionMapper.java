package com.ascrm.mapper;

import com.ascrm.entity.MultipleChoiceQuestion;
import com.mybatisflex.core.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 多选题表 映射层。
 *
 * @author ascrm
 * @since 1.0
 */
@Mapper
public interface MultipleChoiceQuestionMapper extends BaseMapper<MultipleChoiceQuestion> {
}
