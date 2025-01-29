package com.ascrm.service.impl;


import org.springframework.stereotype.Service;
import com.ascrm.service.JudgeQuestionService;
import com.ascrm.entity.JudgeQuestion;
import com.ascrm.mapper.JudgeQuestionMapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;

/**
 * 判断题表 服务层实现。
 *
 * @author ascrm
 * @since 1.0
 */
@Service
public class JudgeQuestionServiceImpl extends ServiceImpl<JudgeQuestionMapper, JudgeQuestion> implements JudgeQuestionService {

}