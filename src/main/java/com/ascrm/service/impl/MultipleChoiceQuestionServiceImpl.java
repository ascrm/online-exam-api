package com.ascrm.service.impl;


import org.springframework.stereotype.Service;
import com.ascrm.service.MultipleChoiceQuestionService;
import com.ascrm.entity.MultipleChoiceQuestion;
import com.ascrm.mapper.MultipleChoiceQuestionMapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;

/**
 * 多选题表 服务层实现。
 *
 * @author ascrm
 * @since 1.0
 */
@Service
public class MultipleChoiceQuestionServiceImpl extends ServiceImpl<MultipleChoiceQuestionMapper, MultipleChoiceQuestion> implements MultipleChoiceQuestionService {

}