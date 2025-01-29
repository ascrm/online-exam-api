package com.ascrm.service.impl;


import cn.hutool.core.bean.BeanUtil;
import com.ascrm.entity.DTO.QuestionDTO;
import com.ascrm.handler.QuestionHandler;
import com.ascrm.handler.QuestionHandlerFactory;
import com.ascrm.utils.UserHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ascrm.service.QuestionService;
import com.ascrm.entity.Question;
import com.ascrm.mapper.QuestionMapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;

/**
 * 服务层实现。
 *
 * @author ascrm
 * @since 1.0
 */
@Service
@RequiredArgsConstructor
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements QuestionService {

    private final QuestionMapper questionMapper;

    private final QuestionHandlerFactory questionHandlerFactory;

    @Override
    @Transactional
    public void addQuestion(QuestionDTO questionDTO) {
        questionDTO.setCreatedBy(UserHolder.getUsername());
        Question question = BeanUtil.copyProperties(questionDTO, Question.class);
        questionMapper.insert(question,true);
        questionDTO.setId(question.getId());
        QuestionHandler handler = questionHandlerFactory.getHandler(questionDTO.getQuestionType());
        handler.addQuestion(questionDTO);
    }
}