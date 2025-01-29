package com.ascrm.handler;

import com.ascrm.entity.DTO.QuestionDTO;
import com.ascrm.entity.JudgeQuestion;
import com.ascrm.enums.QuestionTypeEnum;
import com.ascrm.mapper.JudgeQuestionMapper;
import com.mybatisflex.core.update.UpdateChain;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.Arrays;

import static com.ascrm.entity.table.JudgeQuestionTableDef.JUDGE_QUESTION;

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

    @Override
    public void deleteQuestion(Integer id) {
        UpdateChain.of(JudgeQuestion.class)
                .from(JUDGE_QUESTION)
                .set(JUDGE_QUESTION.IS_DELETE, true)
                .where(JUDGE_QUESTION.QUESTION_ID.eq(id))
                .update();
    }

    @Override
    public void deleteQuestions(String ids) {
        UpdateChain.of(JudgeQuestion.class)
                .from(JUDGE_QUESTION)
                .set(JUDGE_QUESTION.IS_DELETE, true)
                .where(JUDGE_QUESTION.QUESTION_ID.in(Arrays.asList(ids.split(","))))
                .update();
    }
}
