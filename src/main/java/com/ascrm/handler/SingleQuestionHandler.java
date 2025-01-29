package com.ascrm.handler;

import com.ascrm.entity.DTO.QuestionDTO;
import com.ascrm.entity.SingleChoiceQuestion;
import com.ascrm.enums.QuestionTypeEnum;
import com.ascrm.mapper.SingleChoiceQuestionMapper;
import com.mybatisflex.core.update.UpdateChain;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.Arrays;

import static com.ascrm.entity.table.SingleChoiceQuestionTableDef.SINGLE_CHOICE_QUESTION;

/**
 * @Author: ascrm
 * @Date: 2025/1/29
 */
@Component
public class SingleQuestionHandler implements QuestionHandler{

    @Resource
    private SingleChoiceQuestionMapper singleChoiceQuestionMapper;

    @Override
    public QuestionTypeEnum getQuestionTypeEnum() {
        return QuestionTypeEnum.SINGLE_CHOICE;
    }

    @Override
    public void addQuestion(QuestionDTO questionDTO) {
        SingleChoiceQuestion singleChoiceQuestion = new SingleChoiceQuestion();
        singleChoiceQuestion.setQuestionId(questionDTO.getId())
                .setAnswer(questionDTO.getAnswer())
                .setOptionA(questionDTO.getOptionA())
                .setOptionB(questionDTO.getOptionB())
                .setOptionC(questionDTO.getOptionC())
                .setOptionD(questionDTO.getOptionD())
                .setCreatedBy(questionDTO.getCreatedBy());
        singleChoiceQuestionMapper.insert(singleChoiceQuestion,true);
    }

    @Override
    public void deleteQuestion(Integer id) {
        UpdateChain.of(SingleChoiceQuestion.class)
                .from(SINGLE_CHOICE_QUESTION)
                .set(SINGLE_CHOICE_QUESTION.IS_DELETE, true)
                .where(SINGLE_CHOICE_QUESTION.QUESTION_ID.eq(id))
                .update();
    }

    @Override
    public void deleteQuestions(String ids) {
        UpdateChain.of(SingleChoiceQuestion.class)
                .from(SINGLE_CHOICE_QUESTION)
                .set(SINGLE_CHOICE_QUESTION.IS_DELETE, true)
                .where(SINGLE_CHOICE_QUESTION.QUESTION_ID.in(Arrays.asList(ids.split(","))))
                .update();
    }
}
