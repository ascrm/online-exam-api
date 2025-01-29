package com.ascrm.handler;

import com.ascrm.entity.DTO.QuestionDTO;
import com.ascrm.entity.JudgeQuestion;
import com.ascrm.enums.QuestionTypeEnum;
import com.ascrm.mapper.JudgeQuestionMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * @Author: ascrm
 * @Date: 2025/1/29
 */
@Component
public class JudgeQuestionHandler implements QuestionHandler{

    @Resource
    private JudgeQuestionMapper judgeQuestionMapper;

    @Override
    public QuestionTypeEnum getQuestionTypeEnum() {
        return QuestionTypeEnum.JUDGE;
    }

    @Override
    public void addQuestion(QuestionDTO questionDTO) {
        JudgeQuestion judgeQuestion = new JudgeQuestion();
        judgeQuestion.setQuestionId(questionDTO.getId())
                .setAnswer(questionDTO.getAnswer())
                .setCreatedBy(questionDTO.getCreatedBy());
        judgeQuestionMapper.insert(judgeQuestion,true);
    }
}
