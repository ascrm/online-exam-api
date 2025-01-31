package com.ascrm.service.impl;


import cn.hutool.core.bean.BeanUtil;
import com.ascrm.converter.QuestionConverter;
import com.ascrm.entity.DTO.QuestionDTO;
import com.ascrm.enums.QuestionTypeEnum;
import com.ascrm.handler.QuestionHandler;
import com.ascrm.handler.QuestionHandlerFactory;
import com.ascrm.utils.UserHolder;
import com.ascrm.viewer.QuestionViewer;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import com.ascrm.service.QuestionService;
import com.ascrm.entity.Question;
import com.ascrm.mapper.QuestionMapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static com.ascrm.entity.table.QuestionTableDef.QUESTION;

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

    private final QuestionConverter questionConverter;

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

    @Override
    @Transactional
    public void updateQuestion(QuestionDTO questionDTO) {
        Question question = BeanUtil.copyProperties(questionDTO, Question.class);
        questionMapper.update(question);
        QuestionHandler handler = questionHandlerFactory.getHandler(questionDTO.getQuestionType());
        handler.updateQuestion(questionDTO);
    }

    @Override
    @Transactional
    public void deleteQuestion(int id) {
        updateChain().set(QUESTION.IS_DELETED,1)
                .where(QUESTION.ID.eq(id))
                .update();
        QuestionHandler handler = questionHandlerFactory.getHandler(questionMapper.selectOneById(id).getQuestionType());
        handler.deleteQuestion(id);
    }

    @Override
    @Transactional
    public void deleteQuestions(String ids) {
        updateChain()
                .set(QUESTION.IS_DELETED,true)
                .where(QUESTION.ID.in(Arrays.asList(ids.split(","))))
                .update();
        List<Question> questionList = queryChain().select(QUESTION.ALL_COLUMNS)
                .from(QUESTION)
                .where(QUESTION.ID.in(Arrays.asList(ids.split(","))))
                .list();

        for (QuestionTypeEnum questionTypeEnum : QuestionTypeEnum.values()) {
            List<Integer> idList = questionList.stream().filter(question ->
                    questionTypeEnum.getValue().equals(question.getQuestionType())
            ).map(Question::getId).toList();
            if(!CollectionUtils.isEmpty(idList)){
                QuestionHandler handler = questionHandlerFactory.getHandler(questionTypeEnum.getValue());
                handler.deleteQuestions(idList);
            }
        }
    }

    @Override
    public QuestionViewer getQuestionViewerById(int id) {
        QuestionViewer questionViewer = questionConverter.to(getById(id));
        QuestionHandler handler = questionHandlerFactory.getHandler(questionViewer.getQuestionType());
        return handler.getQuestionViewerById(questionViewer);
    }
}