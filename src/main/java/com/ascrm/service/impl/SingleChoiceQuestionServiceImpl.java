package com.ascrm.service.impl;


import org.springframework.stereotype.Service;
import com.ascrm.service.SingleChoiceQuestionService;
import com.ascrm.entity.SingleChoiceQuestion;
import com.ascrm.mapper.SingleChoiceQuestionMapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;

/**
 * 服务层实现。
 *
 * @author ascrm
 * @since 1.0
 */
@Service
public class SingleChoiceQuestionServiceImpl extends ServiceImpl<SingleChoiceQuestionMapper, SingleChoiceQuestion> implements SingleChoiceQuestionService {

}