package com.ascrm.service.impl;


import org.springframework.stereotype.Service;
import com.ascrm.service.QuestionService;
import com.ascrm.entity.Question;
import com.ascrm.mapper.QuestionMapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;

/**
 * 服务层实现。
 *
 * @author ascrm
 * @since 1.0
 */
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements QuestionService {

}