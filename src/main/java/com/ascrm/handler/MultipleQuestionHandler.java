package com.ascrm.handler;

import com.ascrm.entity.DTO.QuestionDTO;
import com.ascrm.entity.MultipleChoiceQuestion;
import com.ascrm.enums.QuestionTypeEnum;
import com.ascrm.mapper.MultipleChoiceQuestionMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * @Author: ascrm
 * @Date: 2025/1/29
 */
@Component
public class MultipleQuestionHandler implements QuestionHandler{

    @Resource
    private MultipleChoiceQuestionMapper multipleChoiceQuestionMapper;

    @Override
    public QuestionTypeEnum getQuestionTypeEnum() {
        return QuestionTypeEnum.MULTIPLE_CHOICE;
    }

    @Override
    public void addQuestion(QuestionDTO questionDTO) {
        MultipleChoiceQuestion multipleChoiceQuestion = new MultipleChoiceQuestion();
        multipleChoiceQuestion.setQuestionId(questionDTO.getId())
                .setAnswer(questionDTO.getAnswer())
                .setOptionA(questionDTO.getOptionA())
                .setOptionB(questionDTO.getOptionB())
                .setOptionC(questionDTO.getOptionC())
                .setOptionD(questionDTO.getOptionD())
                .setCreatedBy(questionDTO.getCreatedBy());
        multipleChoiceQuestionMapper.insert(multipleChoiceQuestion,true);
    }
}
